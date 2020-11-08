package mx.uady.sicei.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.uady.sicei.model.Profesor;
import mx.uady.sicei.model.request.ProfesorRequest;
import mx.uady.sicei.service.ProfesorService;

@RestController // Metaprogramacion
@RequestMapping("/api")
public class ProfesorRest {

    @Autowired
    private ProfesorService profesorService;

    // GET /api/alumnos
    @GetMapping("/profesores")
    public ResponseEntity<List<Profesor>> getProfesores() {
        return ResponseEntity.ok().body(profesorService.getProfesores());
    }

    // POST /api/Profesor
    @PostMapping("/profesores")
    public ResponseEntity<Profesor> postProfesores(@RequestBody @Valid ProfesorRequest request) throws URISyntaxException {
        Profesor profesor = profesorService.crearProfesor(request);
        return ResponseEntity
            .created(new URI("/profesores/" + profesor.getId()))
            .body(profesor);
    }

    @GetMapping("/profesores/{id}")
    public ResponseEntity<Profesor> getProfesor(@PathVariable Integer id){
        return ResponseEntity.ok().body(profesorService.getProfesor(id));
    }

    @PutMapping("/profesores/{id}")
    public ResponseEntity<Profesor> puttAlumnos(@PathVariable Integer id, @RequestBody @Valid ProfesorRequest request) {
        return ResponseEntity.ok().body(profesorService.updateProfesor(id, request));
    }

    @DeleteMapping("/profesores/{id}")
    public ResponseEntity<Profesor> deleteAlumno(@PathVariable Integer id){
        return ResponseEntity.ok().body(profesorService.deleteProfesor(id));
    }


}