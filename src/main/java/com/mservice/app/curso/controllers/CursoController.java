package com.mservice.app.curso.controllers;

import com.mservice.app.curso.models.entity.Curso;
import com.mservice.app.curso.services.ICursoService;
import com.mservice.generic.controllers.GenericController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class CursoController extends GenericController<Curso, ICursoService> {

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@RequestBody Curso curso, @PathVariable Long id){

        Optional<Curso> dbCurso = service.findById(id);

        if (dbCurso.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Curso editCurso = dbCurso.get();

        editCurso.setNombre(curso.getNombre());

        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(editCurso));
    }
}
