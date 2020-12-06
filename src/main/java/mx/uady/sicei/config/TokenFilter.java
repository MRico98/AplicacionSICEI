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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        Usuario user;
        
        if(authHeader == null || authHeader.isEmpty()) {
            chain.doFilter(request, response);
            return;
        }
        String[] body =authHeader.split("\\.");

        //Se obtiene el payload
        String payload = base64UrlDecode(body[1]);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(payload);
        String userName = jsonNode.get("name").asText();

        Optional<Usuario> usuario = usuarioRepository.findByUsuario(userName);
        if(!usuario.isPresent()){
            chain.doFilter(request, response);
            return;
        }

        user = usuario.get();
        JwtTokenUtil jwtUtil = new JwtTokenUtil(user.getSecret());
        boolean isValid = jwtUtil.validateToken(authHeader, user.getUsuario());

        if (!isValid) {
            chain.doFilter(request, response);
            return;
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    public static String base64UrlDecode(String input) {
        String result = null;
        Base64 decoder = new Base64(true);
        byte[] decodedBytes = decoder.decode(input);
        result = new String(decodedBytes);
        return result;
    }
}