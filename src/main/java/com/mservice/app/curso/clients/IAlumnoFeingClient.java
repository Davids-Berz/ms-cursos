package com.mservice.app.curso.clients;

import com.mservice.commons.alumnos.models.entity.Alumno;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "ms-cursos")
public interface IAlumnoFeingClient {

    @GetMapping("/alumnos-por-curso")
    Iterable<Alumno> obtenerAlumnosPorCurso(Iterable<Long> ids);
}
