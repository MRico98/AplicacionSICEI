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


@Component
public class JwtTokenUtil implements Serializable {

	Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class);

	private static final long serialVersionUID = -2550185165626007488L;

	public static final int EXPIRATION_IN_MINUTES  = 5 ;

	private String secret;

	public JwtTokenUtil(String secret){
		this.secret = secret;
	}

	public JwtTokenUtil(){}

	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}


	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}
    //for retrieveing any information from token we will need the secret key
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	//check if the token has expired
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	//generate token for user
	public String generateToken(String username) {
		return createToken(username);
	}

	private String createToken(String subject) {
		Date currentDate = new Date();
		Calendar calendar = Calendar.getInstance(); 
		calendar.setTime(currentDate); 
		calendar.add(Calendar.MINUTE, EXPIRATION_IN_MINUTES);
		return Jwts.builder()
				.claim("name", subject)
				.setSubject(subject)
				.setIssuer("InnerCircle")
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(calendar.getTime())
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	//validate token
	public Boolean validateToken(String token, String name) {
		try {
			final String username = getUsernameFromToken(token);
			return (username.equals(name) && !isTokenExpired(token));
		} catch (Exception e) {
			logger.info( e.getMessage());
			return false;
		}
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}
}