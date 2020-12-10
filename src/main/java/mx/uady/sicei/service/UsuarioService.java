package mx.uady.sicei.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import mx.uady.sicei.exception.AlreadyExistsException;
import mx.uady.sicei.exception.NotFoundException;
import mx.uady.sicei.model.Alumno;
import mx.uady.sicei.model.Token;
import mx.uady.sicei.model.Usuario;
import mx.uady.sicei.model.request.UsuarioRequest;
import mx.uady.sicei.repository.AlumnoRepository;
import mx.uady.sicei.repository.UsuarioRepository;
import mx.uady.sicei.util.JwtTokenUtil;

@Service
public class UsuarioService {
    @Autowired
    private JwtTokenUtil jwtUtil;

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
        String secret = UUID.randomUUID().toString();
        usuarioCrear.setSecret(secret);

        validateCreateStudent(request.getMatricula());

        Usuario usuarioGuardado = usuarioRepository.save(usuarioCrear);

        Alumno alumno = new Alumno();

        alumno.setNombre(request.getNombre());
        alumno.setUsuario(usuarioGuardado); // Relacionar 2 entidades
        alumno.setMatricula(request.getMatricula());
        alumno.setLicenciatura(request.getLicenciatura());
        alumnoRepository.save(alumno);

        return usuarioGuardado;
    }
    
    public Token loadUser(String email, String password){
        Optional<Usuario> opt = usuarioRepository.findByUsuarioAndPassword(email, password);
        if (opt.isPresent()) {
            Usuario user = opt.get();
            //Se crea el token
            Token token = new Token();
            token.setToken(jwtUtil.createToken(user));
            return token;
        }

        throw new NotFoundException();
    }

    public Usuario logout(){
       return (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public Usuario getUsuario(Integer id) {

        Optional<Usuario> opt = usuarioRepository.findById(id);

        if (opt.isPresent()) {
            return opt.get();
        }

        throw new NotFoundException();
    }

    public void validateCreateUsuario(String usuario){
        if(usuarioRepository.findByUsuario(usuario).isPresent()){
            throw new AlreadyExistsException("El usuario ya ha sido registrado");
        }
    }

    public void validateCreateStudent(String matricula){
        if(alumnoRepository.findByMatricula(matricula).isPresent()){
            throw new AlreadyExistsException("Ya existe un alumno con la misma matricula");
        }
    }

}