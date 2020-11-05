package mx.uady.sicei.service;

import java.util.LinkedList;
import java.util.List;
import org.springframework.stereotype.Service;

import mx.uady.sicei.exception.NotFoundException;
import mx.uady.sicei.model.Profesor;
import mx.uady.sicei.model.request.ProfesorRequest;

@Service
public class ProfesorService {

    private List<Profesor> profesores = new LinkedList<>();

    {
        profesores = new LinkedList<>();
        profesores.add(new Profesor().id(1).nombre("Eduardo"));
        profesores.add(new Profesor().id(2).nombre("Antonio"));
        profesores.add(new Profesor().id(3).nombre("Brayan"));
    }

    public List<Profesor> getProfesor() {
        return profesores;
    }

    public Profesor crearProfesor(ProfesorRequest request) {
        Profesor profesor = new Profesor();

        profesor.setId(profesores.size() + 1);
        profesor.setNombre(request.getNombre());
        profesores.add(profesor);

        return profesor;
    }

    public Profesor buscarProfesorId(int id) throws RuntimeException{
        Profesor profesor = profesores.get(searchByParameter(id));
        return profesor;
    }

    public Profesor modificarProfesor(int id,ProfesorRequest profesormodificado) throws RuntimeException{
        int index =  searchByParameter(id);
        profesores.get(index).setNombre(profesormodificado.getNombre());
        return profesores.get(index);
    }

    public Profesor eliminarProfesor(int id){
        int index = searchByParameter(id);
        Profesor profesor = profesores.get(index);
        profesores.remove(index);
        return profesor;
    }

    private int searchByParameter(int id){
        for(int contador = 0;contador < profesores.size();contador++){
            if(id == profesores.get(contador).getId()) return contador;
        }
        throw new NotFoundException();
    }
}
