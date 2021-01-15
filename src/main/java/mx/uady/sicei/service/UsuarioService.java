package mx.uady.sicei.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.transaction.Transactional;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
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
import mx.uady.sicei.util.EmailSending;
import mx.uady.sicei.util.JwtTokenUtil;

@Service
public class UsuarioService {
    @Autowired
    private JwtTokenUtil jwtUtil;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private EmailService emailService;

    public List<Usuario> getUsuarios() {
        return usuarioRepository.findAll();
    }

    @Transactional
    public Usuario crear(UsuarioRequest request) {
        Usuario usuarioCrear = new Usuario();

        validateCreateUsuario(request.getUsuario());

        usuarioCrear.setUsuario(request.getUsuario());
        usuarioCrear.setEmail(request.getEmail());
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

        emailService.sendEmail(request.getEmail(), "Bienvenido al sitio", "Saludos chavo");
        return usuarioGuardado;
    }

    public Usuario updateUsuario(Integer id, UsuarioRequest request) {
        Optional<Usuario> currentUser = usuarioRepository.findById(id);
        if(currentUser.isPresent()){
            Usuario user = currentUser.get();
            user.setUsuario(request.getUsuario());
            user.setEmail(request.getEmail());
            user.setPassword(request.getPassword());

            emailService.sendEmail(request.getEmail(), "Datos del usuario actualizados", "Tus datos de usuario se han actualizado");
            return user;
        }
        throw new NotFoundException();
    }

    public Usuario deleteUsuario(Integer id){
        Optional<Usuario> currentUser = usuarioRepository.findById(id);
        if(currentUser.isPresent()){
            Usuario user = currentUser.get();
            emailService.sendEmail(user.getEmail(), "Usuario eliminado", "Tu usuario fue eliminado con exito");
            usuarioRepository.deleteById(id);
            return user;
        }
        throw new NotFoundException();
    }
    
    public Token loadUser(String email, String password){
        Optional<Usuario> opt = usuarioRepository.findByEmailAndPassword(email, password);
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