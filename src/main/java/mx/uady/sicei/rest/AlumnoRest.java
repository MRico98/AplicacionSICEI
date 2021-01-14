package mx.uady.sicei.rest;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.util.List;

import javax.mail.MessagingException;
import javax.validation.Valid;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import mx.uady.sicei.util.EmailSending;
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
import mx.uady.sicei.model.Alumno;
import mx.uady.sicei.model.request.AlumnoRequest;
import mx.uady.sicei.service.AlumnoSerivce;

@RestController // Metaprogramacion
@RequestMapping("/api")
public class AlumnoRest {

    @Autowired
    private AlumnoSerivce alumnoService;

    // GET /api/alumnos
    @GetMapping("/alumnos")
    public ResponseEntity<List<Alumno>> getAlumnos() {

        return ResponseEntity.ok().body(alumnoService.getAlumnos());
    }

    // POST /api/alumnos
    @PostMapping("/alumnos")
    public ResponseEntity<Alumno> postAlumnos(@RequestBody @Valid AlumnoRequest request) throws URISyntaxException, IOException, MessagingException {
        Alumno alumno = alumnoService.crearAlumno(request);

        return ResponseEntity
            .created(new URI("/alumnos/" + alumno.getId()))
            .body(alumno);
    }

    // GET /api/alumnos/3 -> 200
    // Validar que exista, si no existe Lanzar un RuntimeException
    @GetMapping("/alumnos/{id}")
    public ResponseEntity<Alumno> getAlumno(@PathVariable Integer id){
        return ResponseEntity.ok().body(alumnoService.getAlumno(id));
    }

    // Validar que exista, si no existe Lanzar un RuntimeException
    @PutMapping("/alumnos/{id}")
    public ResponseEntity<Alumno> postAlumnos(@PathVariable Integer id, @Valid @RequestBody AlumnoRequest request) {
        return ResponseEntity.ok().body(alumnoService.updateAlumno(id, request));
    }

    // Validar que exista, si no existe Lanzar un RuntimeException
    @DeleteMapping("/alumnos/{id}")
    public ResponseEntity<Alumno> deleteAlumno(@PathVariable Integer id){
        return ResponseEntity.ok().body(alumnoService.deleteAlumno(id));
    }

    @PostMapping("/alumnos/{studentId}/equipos/{systemId}")
    public ResponseEntity<Alumno> postSystemToStudent(@PathVariable Integer studentId, @PathVariable Integer systemId) throws URISyntaxException {
        return ResponseEntity.ok().body(alumnoService.addSystemToStudent(systemId, studentId));
    }

    @GetMapping("/alumnos/email")
    public ResponseEntity<String> getEmail() throws GeneralSecurityException, IOException, MessagingException {

        return null;
    }
}