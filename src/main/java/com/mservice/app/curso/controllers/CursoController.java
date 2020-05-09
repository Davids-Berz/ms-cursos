package com.mservice.app.curso.controllers;

import com.mservice.app.curso.models.entity.Curso;
import com.mservice.app.curso.services.ICursoService;
import com.mservice.commons.alumnos.models.entity.Alumno;
import com.mservice.commons.examenes.models.entity.Examen;
import com.mservice.generic.controllers.GenericController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class CursoController extends GenericController<Curso, ICursoService> {

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Curso curso, BindingResult result, @PathVariable Long id){

        if(result.hasErrors()){
            return this.validar(result);
        }
        Optional<Curso> dbCurso = service.findById(id);

        if (dbCurso.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Curso editCurso = dbCurso.get();

        editCurso.setNombre(curso.getNombre());

        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(editCurso));
    }

    @PutMapping("/{id}/asignar-alumnos")
    public ResponseEntity<?> asignarAlumnos(@RequestBody List<Alumno> lstAlumno, @PathVariable Long id){

        Optional<Curso> dbCurso = service.findById(id);
        if (dbCurso.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Curso editCurso = dbCurso.get();

        lstAlumno.forEach(editCurso::addAlumno);

        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(editCurso));
    }

    @PutMapping("/{id}/eliminar-alumno")
    public ResponseEntity<?> eliminarAlumnos(@RequestBody Alumno alumno, @PathVariable Long id){

        Optional<Curso> dbCurso = service.findById(id);
        if (dbCurso.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Curso editCurso = dbCurso.get();

        editCurso.removeAlumno(alumno);

        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(editCurso));
    }

    @GetMapping("/alumno/{id}")
    public ResponseEntity<?> findByAlumnoId(@PathVariable Long id){
        return ResponseEntity.ok().body(service.findCursoByAlumnoId(id));
    }

    @PutMapping("/{id}/asignar-examenes")
    public ResponseEntity<?> asignarExamenes(@RequestBody List<Examen> lstExamenes, @PathVariable Long id){

        Optional<Curso> dbCurso = service.findById(id);
        if (dbCurso.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Curso editCurso = dbCurso.get();

        lstExamenes.forEach(editCurso::addExamenes);

        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(editCurso));
    }

    @PutMapping("/{id}/eliminar-examen")
    public ResponseEntity<?> eliminarExamen(@RequestBody Examen examen, @PathVariable Long id){

        Optional<Curso> dbCurso = service.findById(id);
        if (dbCurso.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Curso editCurso = dbCurso.get();

        editCurso.removeExamen(examen);

        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(editCurso));
    }

}



