package com.mservice.app.curso.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-respuestas")
public interface IRespuestaFeingClient {

    @GetMapping("/alumno/{alumnoId}/examenes-respondidos")
    Iterable<Long> obtenerExamenesIdConRespuestaAlumno(@PathVariable Long alumnoId);

}
