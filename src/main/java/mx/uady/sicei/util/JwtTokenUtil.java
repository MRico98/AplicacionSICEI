package mx.uady.sicei.util;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.function.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import mx.uady.sicei.model.Usuario;


@Component
public class JwtTokenUtil implements Serializable {

	Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class);

	private static final long serialVersionUID = -2550185165626007488L;

	public static final int EXPIRATION_IN_MINUTES  = 5 ;


	public JwtTokenUtil(){}

	public String getUsernameFromToken(String token, String secret) {
		return getClaimFromToken(token, secret,Claims::getSubject);
	}


	public Date getExpirationDateFromToken(String token, String secret) {
		return getClaimFromToken(token, secret ,Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, String secret, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token, secret);
		return claimsResolver.apply(claims);
	}
    //for retrieveing any information from token we will need the secret key
	private Claims getAllClaimsFromToken(String token, String secret) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	//check if the token has expired
	private Boolean isTokenExpired(String token, String secret) {
		final Date expiration = getExpirationDateFromToken(token,secret);
		return expiration.before(new Date());
	}

	public String createToken(Usuario user) {
		Date currentDate = new Date();
		Calendar calendar = Calendar.getInstance(); 
		calendar.setTime(currentDate); 
		calendar.add(Calendar.MINUTE, EXPIRATION_IN_MINUTES);
		return Jwts.builder()
				.setId(Integer.toString(user.getId()))
				.setSubject(user.getUsuario())
				.setIssuer("InnerCircle")
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(calendar.getTime())
				.signWith(SignatureAlgorithm.HS512, user.getSecret()).compact();
	}

	//validate token
	public Boolean validateToken(String token, String name, String secret) {
		try {
			final String username = getUsernameFromToken(token,secret);
			return (username.equals(name) && !isTokenExpired(token,secret));
		} catch (Exception e) {
			logger.info( e.getMessage());
			return false;
		}
	}
}