package mx.uady.sicei.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uady.sicei.model.Alumno;
import mx.uady.sicei.model.Usuario;
import mx.uady.sicei.exception.NotFoundException;
import mx.uady.sicei.model.request.AlumnoRequest;
import mx.uady.sicei.repository.AlumnoRepository;
import mx.uady.sicei.repository.UsuarioRepository;

@Service
public class AlumnoSerivce {

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Alumno> getAlumnos() {

        List<Alumno> alumnos = new LinkedList<>();
        alumnoRepository.findAll().iterator().forEachRemaining(alumnos::add); // SELECT(id, nombre)
        
        return alumnos;
    }

    public Alumno crearAlumno(AlumnoRequest request) {

        //Se crea el usuario
        Usuario user = new Usuario(request.getNombre());
        user = usuarioRepository.save(user);

        Alumno alumno = new Alumno();
        alumno.setNombre(request.getNombre());
        alumno.setLicenciatura(request.getLicenciatura());
        alumno.setUsuario(user);
        alumno = alumnoRepository.save(alumno);

        return alumno;
    }

    public Alumno getAlumno(int id){
        validateExistanceStudent(id);
        return alumnoRepository.findById(id).get();
    }

    public Alumno updateAlumno(int id, AlumnoRequest request){
        validateExistanceStudent(id);
        Alumno currentAlumno = alumnoRepository.findById(id).get();
        currentAlumno.setNombre(request.getNombre());
        currentAlumno.setLicenciatura(request.getLicenciatura());

        return alumnoRepository.save(currentAlumno);

    }

    public Alumno deleteAlumno(int id){
        validateExistanceStudent(id);
        Alumno deletedAlumno = alumnoRepository.findById(id).get();
        usuarioRepository.delete(deletedAlumno.getUsuario());
        alumnoRepository.deleteById(id);

        return deletedAlumno;
    }

    private void validateExistanceStudent(int studentId){
        if( !alumnoRepository.existsById(studentId)){
            throw new NotFoundException("No se encontro al estudiante");
        }
    }
    
}
