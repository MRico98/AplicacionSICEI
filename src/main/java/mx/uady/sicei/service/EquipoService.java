package mx.uady.sicei.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uady.sicei.model.Equipo;
import mx.uady.sicei.exception.DeleteException;
import mx.uady.sicei.exception.NotFoundException;
import mx.uady.sicei.model.request.EquipoRequest;
import mx.uady.sicei.repository.EquipoRepository;

@Service
public class EquipoService {

    @Autowired
    private EquipoRepository equipoRepository;

    public List<Equipo> getEquipos() {

        List<Equipo> equipos = new LinkedList<>();
        equipoRepository.findAll().iterator().forEachRemaining(equipos::add);

        return equipos;
    }

    public Equipo createEquipo(EquipoRequest request){
        Equipo equipo = new Equipo();
        equipo.setModelo(request.getModelo());
        return equipoRepository.save(equipo);
    }

    public Equipo getEquipo(int id){
        validateExistanceEquipo(id);
        return equipoRepository.findById(id).get();
    }

    public Equipo updateEquipo(int id, EquipoRequest request){
        validateExistanceEquipo(id);
        Equipo equipo = getEquipo(id);
        equipo.setModelo(request.getModelo());
        return equipoRepository.save(equipo);
    }

    public Equipo deleteEquipo(int id){
        validateExistanceEquipo(id);
        Equipo equipo = getEquipo(id);
        if(equipo.getAlumnos().size()>0){
            throw new DeleteException("El equipo tiene alumnos asignados");
        }
        equipoRepository.delete(equipo);
        return equipo;
    }

    public void validateExistanceEquipo(int equipoId){
        if( !equipoRepository.existsById(equipoId)){
            throw new NotFoundException("No se encontro al equipo");
        }
    }
    
}
