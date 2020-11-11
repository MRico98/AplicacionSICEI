package mx.uady.sicei.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uady.sicei.model.Tutoria;
import mx.uady.sicei.model.TutoriaLlave;
import mx.uady.sicei.exception.DeleteException;
import mx.uady.sicei.exception.NotFoundException;
import mx.uady.sicei.model.request.TutoriaRequest;
import mx.uady.sicei.repository.TutoriaRepository;
import mx.uady.sicei.repository.AlumnoRepository;
import mx.uady.sicei.repository.ProfesorRepository;

@Service
public class TutoriaService {

    @Autowired
    private TutoriaRepository tutoriaRepository;
    @Autowired
    private AlumnoRepository alumnoRepository;
    @Autowired
    private ProfesorRepository profesorRepository;

    public List<Tutoria> getTutorias() {

        List<Tutoria> tutorias = new LinkedList<>();
        tutoriaRepository.findAll().iterator().forEachRemaining(tutorias::add);

        return tutorias;
    }

    public Tutoria createTutoria(TutoriaRequest request){
        Tutoria tutoria = new Tutoria();

        validateCreateTutoria(request.getProfesor(), request.getAlumno());

        tutoria.setProfesor(request.getProfesor());
        tutoria.setAlumno(request.getAlumno());
        tutoria.setHoras(request.getHoras());
        return tutoriaRepository.save(tutoria);
    }

    public Tutoria getTutoria(int profesor_id, int alumno_id){
        validateExistanceTutoria(profesor_id, alumno_id);
        return tutoriaRepository.findById(new TutoriaLlave(profesor_id, alumno_id)).get();
    }

    public Tutoria updateTutoria(int profesor_id, int alumno_id, TutoriaRequest request){
        validateExistanceTutoria(profesor_id, alumno_id);
        Tutoria tutoria = getTutoria(profesor_id, alumno_id);
        tutoria.setProfesor(request.getProfesor());
        tutoria.setAlumno(request.getAlumno());
        tutoria.setHoras(request.getHoras());
        return tutoriaRepository.save(tutoria);
    }

    public Tutoria deleteTutoria(int profesor_id, int alumno_id){
        validateExistanceTutoria(profesor_id, alumno_id);
        Tutoria tutoria = getTutoria(profesor_id, alumno_id);
        if(tutoria.getAlumno()>0){
            throw new DeleteException("La tutoria tiene un alumno asignado");
        }
        tutoriaRepository.delete(tutoria);
        return tutoria;
    }

    public void validateExistanceTutoria(int profesor_id, int alumno_id){
        if( !tutoriaRepository.existsById(new TutoriaLlave(profesor_id, alumno_id))){
            throw new NotFoundException("No se encontro la tutoria");
        }
    }

    public void validateCreateTutoria(int profesor_id, int alumno_id){
        if( !profesorRepository.existsById(profesor_id)){
            throw new NotFoundException("No se encontro el profesor a asignar");
        }
        if( !alumnoRepository.existsById(alumno_id)){
            throw new NotFoundException("No se encontro el alumno a asignar");
        }
        
    }
    
}