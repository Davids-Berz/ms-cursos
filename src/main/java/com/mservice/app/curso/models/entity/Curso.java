package com.mservice.app.curso.models.entity;

import com.mservice.commons.alumnos.models.entity.Alumno;
import com.mservice.commons.examenes.models.entity.Examen;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@Entity
@Table(name = "cursos")
public class Curso {

    public Curso(){
        this.lstAlumnos = new ArrayList<>();
        this.lstExamenes = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String nombre;

    @Column(name = "create_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Alumno> lstAlumnos;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Examen> lstExamenes;

    //Metodos de Alumnmo
    public void addAlumno(Alumno alumno){
        this.lstAlumnos.add(alumno);
    }

    public void removeAlumno(Alumno alumno){
        this.lstAlumnos.remove(alumno);
    }

    //Metodos de Examen
    public void addExamenes(Examen examen ){
        this.lstExamenes.add(examen);
    }

    public void removeExamen(Examen examen){
        this.lstExamenes.remove(examen);
    }


    @PrePersist
    public void prePersist(){
        this.createAt=new Date();
    }
}
