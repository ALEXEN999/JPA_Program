package com.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "Alumno")
@Entity
public class Alumno {
    @Id
    @Column(name = "dni", insertable = false, nullable = false)
    private int dni;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "clase_id", nullable = false)
    private int claseId;

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getClaseId() {
        return claseId;
    }

    public void setClaseId(int claseId) {
        this.claseId = claseId;
    }

    public String toString() {
      return "Alumno{dni=" + dni + 
        ", nombre=" + nombre + 
        ", claseId=" + claseId + 
        "}";
    }
}