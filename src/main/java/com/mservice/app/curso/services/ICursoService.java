package com.mservice.app.curso.services;

import com.mservice.app.curso.models.entity.Curso;
import com.mservice.commons.alumnos.models.entity.Alumno;
import com.mservice.generic.services.IGenericService;
import org.springframework.web.bind.annotation.RequestParam;

public interface ICursoService extends IGenericService<Curso> {

    Curso findCursoByAlumnoId(Long id);
    Iterable<Long> obtenerExamenesIdConRespuestaAlumno(Long alumnoId);
    Iterable<Alumno> obtenerAlumnosPorCurso(@RequestParam Iterable<Long> ids);
    void deleteCursoAlumnoByAlumnoId(Long id);

}
