package com.mservice.app.curso.models.repository;

import com.mservice.app.curso.models.entity.Curso;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ICursoRepository extends CrudRepository<Curso, Long> {

    @Query("select c from Curso c join fetch c.lstAlumnos a where a.id=?1")
    Curso findCursoByAlumnoId(Long id);
}
