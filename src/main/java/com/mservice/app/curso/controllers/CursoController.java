package com.mservice.app.curso.controllers;

import com.mservice.app.curso.models.entity.Curso;
import com.mservice.app.curso.models.entity.CursoAlumno;
import com.mservice.app.curso.services.ICursoService;
import com.mservice.commons.alumnos.models.entity.Alumno;
import com.mservice.commons.examenes.models.entity.Examen;
import com.mservice.generic.controllers.GenericController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class CursoController extends GenericController<Curso, ICursoService> {

    @Value("${config.balanceador.test}")
    private String balanceadorTest;

    @GetMapping("/balanceador-test")
    public ResponseEntity<?> balanceadorTest() {

        Map<String, Object> response = new HashMap<>();
        response.put("balanceador", balanceadorTest);
        response.put("cursos",service.findAll());
        return ResponseEntity.ok(response);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<?> listarPorId(@PathVariable Long id){

        Optional<Curso> dbEntity = service.findById(id);

        if (dbEntity.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Curso curso = dbEntity.get();

        if(!curso.getLstAlumnos().isEmpty()){
            List<Long> ids = curso.getCursoAlumnos().stream().map(cursoAlumno -> {
                return cursoAlumno.getAlumnoId();
            }).collect(Collectors.toList());

            List<Alumno> lstAlumno = (List<Alumno>) service.obtenerAlumnosPorCurso(ids);
            curso.setLstAlumnos(lstAlumno);
        }

        return ResponseEntity.ok(curso);
    }

    @Override
    @GetMapping("/pagina")
    public ResponseEntity<?> listar(Pageable pageable) {

        Page<Curso> lstCursos = service.findAll(pageable).map(curso -> {
            curso.getCursoAlumnos().forEach(cursoAlumno -> {
                Alumno alumno = new Alumno();
                alumno.setId(cursoAlumno.getAlumnoId());
                curso.addAlumno(alumno);
            });
            return curso;
        });

        return ResponseEntity.ok(lstCursos);
    }

    @Override
    @GetMapping
    public ResponseEntity<?> listar() {
        List<Curso> lstCursos = ((List<Curso>) service.findAll()).stream().map(curso->{
            curso.getCursoAlumnos().forEach(cursoAlumno -> {
                Alumno alumno = new Alumno();
                alumno.setId(cursoAlumno.getAlumnoId());
                curso.addAlumno(alumno);
            });
            return curso;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(lstCursos);
    }

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

        lstAlumno.forEach(alumno->{
            CursoAlumno cursoAlumno = new CursoAlumno();
            cursoAlumno.setAlumnoId(alumno.getId());
            cursoAlumno.setCurso(editCurso);
            editCurso.addCursoAlumno(cursoAlumno);
        });

        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(editCurso));
    }

    @PutMapping("/{id}/eliminar-alumno")
    public ResponseEntity<?> eliminarAlumnos(@RequestBody Alumno alumno, @PathVariable Long id){

        Optional<Curso> dbCurso = service.findById(id);
        if (dbCurso.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Curso editCurso = dbCurso.get();

        CursoAlumno cursoAlumno = new CursoAlumno();

        cursoAlumno.setAlumnoId(alumno.getId());
        editCurso.removeCursoAlumno(cursoAlumno);

        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(editCurso));
    }

    @GetMapping("/alumno/{id}")
    public ResponseEntity<?> findCursoByAlumnoId(@PathVariable Long id){
        Curso curso = service.findCursoByAlumnoId(id);
        if(curso != null){
            List<Long> examenesId = (List<Long>) service.obtenerExamenesIdConRespuestaAlumno(id);
            List<Examen> lstExamenes = curso.getLstExamenes().stream().map(examen->{
               if (examenesId.contains(examen.getId())){
                   examen.setRespondido(true);
               }
               return examen;
            }).collect(Collectors.toList());
            curso.setLstExamenes(lstExamenes);
        }

        return ResponseEntity.ok().body(curso);

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



