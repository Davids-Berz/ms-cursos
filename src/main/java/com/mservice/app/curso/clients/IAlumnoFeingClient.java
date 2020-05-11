package com.mservice.app.curso.clients;

import com.mservice.commons.alumnos.models.entity.Alumno;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ms-cursos")
public interface IAlumnoFeingClient {

    @GetMapping("/alumnos-por-curso")
    Iterable<Alumno> obtenerAlumnosPorCurso(@RequestParam Iterable<Long> ids);
}
