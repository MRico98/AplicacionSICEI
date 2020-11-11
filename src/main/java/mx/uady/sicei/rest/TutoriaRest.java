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

import mx.uady.sicei.model.Tutoria;
import mx.uady.sicei.model.request.TutoriaRequest;
import mx.uady.sicei.service.TutoriaService;

@RestController // Metaprogramacion
@RequestMapping("/api")
public class TutoriaRest {

    @Autowired
    private TutoriaService tutoriaService;

    // GET /api/alumnos
    @GetMapping("/tutorias")
    public ResponseEntity<List<Tutoria>> getTutorias() {
        return ResponseEntity.ok().body(tutoriasService.getTutorias());
    }

    // POST /api/Profesor
    @PostMapping("/tutorias")
    public ResponseEntity<Tutoria> postTutorias(@RequestBody @Valid ProfesorRequest request) throws URISyntaxException {
        Tutoria tutoria = tutoriaService.crearTutoria(request);
        return ResponseEntity
            .created(new URI("/tutorias/" + tutoria.getProfesor() + "/" + tutoria.getAlumno()))
            .body(tutoria);
    }

    @GetMapping("/tutorias/{profesor_id}/{alumno_id}")
    public ResponseEntity<Tutoria> getTutoria(@PathVariable Integer profesor_id, @PathVariable Integer alumno_id){
        return ResponseEntity.ok().body(tutoriaService.getTutoria(profesor_id,alumno_id));
    }

    @PutMapping("/tutorias/{profesor_id}/{alumno_id}")
    public ResponseEntity<Tutoria> putAlumnos(@PathVariable Integer profesor_id, @PathVariable Integer alumno_id, @RequestBody @Valid TutoriaRequest request) {
        return ResponseEntity.ok().body(tutoriaService.updateTutoria(profesor_id,alumno_id, request));
    }

    @DeleteMapping("/profesores/{profesor_id}/{alumno_id}")
    public ResponseEntity<Tutoria> deleteAlumno(@PathVariable Integer profesor_id, @PathVariable Integer alumno_id){
        return ResponseEntity.ok().body(tutoriaService.deleteTutoria(profesor_id,alumno_id));
    }


}