package mx.uady.sicei.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

        // ResponseEntity es una abstraccion de una respuesta HTTP, con body y headers

        return ResponseEntity.ok().body(profesorService.getProfesor());
    }

    // POST /api/alumnos
    @PostMapping("/profesores")
    public ResponseEntity<Profesor> postProfesores(@RequestBody ProfesorRequest request) throws URISyntaxException {
        
        // RequestBody le indica a Java que estamos esperando un request que cumpla con los campos del Objeto AlumnoRequest
        
        Profesor profesor = profesorService.crearProfesor(request);

        // 201 Created
        // Header: Location
        return ResponseEntity
            .created(new URI("/Profesores/" + profesor.getId()))
            .body(profesor);
    }

    // GET /api/alumnos/3 -> 200
    // Validar que exista, si no existe Lanzar un RuntimeException
    @GetMapping("/profesores/{id}")
    public ResponseEntity<Profesor> getProfesor(@PathVariable Integer id){
        return ResponseEntity.ok().body(profesorService.buscarProfesorId(id));
    }

    // Validar que exista, si no existe Lanzar un RuntimeException
    @PutMapping("/profesores/{id}")
    public ResponseEntity<Profesor> puttAlumnos(@PathVariable Integer id, @RequestBody ProfesorRequest request) {
        return ResponseEntity.ok().body(profesorService.modificarProfesor(id, request));
    }

    // Validar que exista, si no existe Lanzar un RuntimeException
    @DeleteMapping("/profesores/{id}")
    public ResponseEntity<Profesor> deleteAlumno(@PathVariable Integer id){
        return ResponseEntity.ok().body(profesorService.eliminarProfesor(id));
    }


}