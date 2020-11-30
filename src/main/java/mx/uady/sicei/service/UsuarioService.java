package mx.uady.sicei.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import mx.uady.sicei.exception.NotFoundException;
import mx.uady.sicei.exception.AlreadyExistsException;
import mx.uady.sicei.model.Alumno;
import mx.uady.sicei.model.Token;
import mx.uady.sicei.model.Usuario;
import mx.uady.sicei.model.request.UsuarioRequest;
import mx.uady.sicei.repository.AlumnoRepository;
import mx.uady.sicei.repository.UsuarioRepository;
import net.bytebuddy.utility.RandomString;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AlumnoRepository alumnoRepository;

    public List<Usuario> getUsuarios() {
        return usuarioRepository.findAll();
    }

    @Transactional
    public Usuario crear(UsuarioRequest request) {
        Usuario usuarioCrear = new Usuario();

        validateCreateUsuario(request.getUsuario());

        usuarioCrear.setUsuario(request.getUsuario());
        usuarioCrear.setPassword(request.getPassword());

        String token = UUID.randomUUID().toString();
        usuarioCrear.setToken(token);

        Usuario usuarioGuardado = usuarioRepository.save(usuarioCrear);

        Alumno alumno = new Alumno();

        alumno.setNombre(request.getNombre());
        alumno.setUsuario(usuarioGuardado); // Relacionar 2 entidades

        alumno = alumnoRepository.save(alumno);

        return usuarioGuardado;
    }
    
    public Token loadUser(String email, String password){
        Optional<Usuario> opt = usuarioRepository.findByUsuarioAndPassword(email, password);
        if (opt.isPresent()) {
            Usuario user = opt.get();
            String uuid = RandomString.make(10);
            //Create Session Token
            user.setToken(uuid);
            usuarioRepository.save(user);

            Token token = new Token();
            token.setToken(uuid);

            return token;
        }else{
            throw new NotFoundException();
        }
    }

    public Usuario logout(){
       Usuario user = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       user.setToken(" ");
       usuarioRepository.save(user);
       return user;
    }

    public Usuario getUsuario(Integer id) {

        Optional<Usuario> opt = usuarioRepository.findById(id);

        if (opt.isPresent()) {
            return opt.get();
        }

        throw new NotFoundException();
    }

    public void validateCreateUsuario(String usuario){
        if(usuarioRepository.findByUsuario(usuario)){
            throw new AlreadyExistsException("El usuario ya ha sido registrado");
        }
    }

}