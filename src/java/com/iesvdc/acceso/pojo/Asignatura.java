/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvdc.acceso.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author profesor
 */
@XmlRootElement(name = "asignatura")
@XmlAccessorType(XmlAccessType.FIELD)
public class Asignatura {

    @XmlElement
    String nombre;
    @XmlElement
    int id;
    @XmlElement
    int curso;
    @XmlElement
    String ciclo;

    public Asignatura() {
    }

    public Asignatura(String nombre, int id, int curso, String ciclo) {
        this.nombre = nombre;
        this.id = id;
        this.curso = curso;
        this.ciclo = ciclo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCurso() {
        return curso;
    }

    public void setCurso(int curso) {
        this.curso = curso;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    @Override
    public String toString() {
        return "Asignatura{" + "nombre=" + nombre + ", id=" + id + ", curso=" + curso + ", ciclo=" + ciclo + '}';
    }

}
