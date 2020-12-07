package mx.uady.sicei.config;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.tomcat.util.codec.binary.Base64;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import mx.uady.sicei.model.Usuario;
import mx.uady.sicei.repository.UsuarioRepository;
import mx.uady.sicei.util.JwtTokenUtil;

@Component
public class TokenFilter extends GenericFilterBean {

    private Logger log = LoggerFactory.getLogger(TokenFilter.class);

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String authHeader = httpRequest.getHeader(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || authHeader.isEmpty()) {
            chain.doFilter(request, response);
            return;
        }

        try {
            JwtConsumer jwtConsumer = new JwtConsumerBuilder().setSkipSignatureVerification().build();
            JwtClaims claims = jwtConsumer.processToClaims(authHeader);
            Optional<Usuario> usuario = usuarioRepository.findById(Integer.valueOf(claims.getJwtId()));

            if(!usuario.isPresent()){
                chain.doFilter(request, response);
                return;
            }

            Usuario user = usuario.get();
            JwtTokenUtil jwtUtil = new JwtTokenUtil(user.getSecret());
            boolean isValid = jwtUtil.validateToken(authHeader, user.getUsuario());
    
            if (!isValid) {
                chain.doFilter(request, response);
                return;
            }
    
            Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, null);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e) {
            log.info(e.getMessage());
            chain.doFilter(request, response);
            return;
        }
        chain.doFilter(request, response);
    }
}