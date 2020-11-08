package mx.uady.sicei.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uady.sicei.exception.NotFoundException;
import mx.uady.sicei.model.Profesor;
import mx.uady.sicei.model.request.ProfesorRequest;
import mx.uady.sicei.repository.ProfesorRepository;

@Service
public class ProfesorService {

    @Autowired
    private ProfesorRepository profesorRepository;

    public List<Profesor> getProfesores() {
        List<Profesor> profesores = new LinkedList<>();
        profesorRepository.findAll().iterator().forEachRemaining(profesores::add);
        
        return profesores;
    }

    public Profesor crearProfesor(ProfesorRequest request) {
        Profesor profesor = new Profesor();
        profesor.setNombre(request.getNombre());
        profesor.setHoras(request.getHoras());

        return profesorRepository.save(profesor);
    }

    public Profesor getProfesor(int id){
        validateExistanceProfesor(id);
        return profesorRepository.findById(id).get();
    }

    public Profesor updateProfesor(int id, ProfesorRequest request){
        validateExistanceProfesor(id);
        Profesor profesor = profesorRepository.findById(id).get();
        profesor.setNombre(request.getNombre());
        profesor.setHoras(request.getHoras());

        return profesorRepository.save(profesor);

    }

    public Profesor deleteProfesor(int id){
        validateExistanceProfesor(id);
        Profesor profesor = profesorRepository.findById(id).get();
        profesorRepository.deleteById(id);
        return profesor;
    }

    private void validateExistanceProfesor(int profesorId){
        if( !profesorRepository.existsById(profesorId)){
            throw new NotFoundException("No se encontro al maestro");
        }
    }
}
