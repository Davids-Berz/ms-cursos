package com.mservice.app.curso.models.repository;

import com.mservice.app.curso.models.entity.Curso;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ICursoRepository extends PagingAndSortingRepository<Curso, Long> {

    @Query("select c from Curso c join fetch c.cursoAlumnos a where a.alumnoId=?1")
    Curso findCursoByAlumnoId(Long id);

    @Modifying
    @Query("delete from CursoAlumno ca where ca.alumnoId=?1")
    void deleteCursoAlumnoByAlumnoId(Long id);
}
