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
@XmlRootElement(name = "alumno")
@XmlAccessorType(XmlAccessType.FIELD)
public class Alumno {

    @XmlElement
    private Integer id;
    @XmlElement
    private String nombre;
    @XmlElement
    private String apellido;

    public Alumno() {
        //this.id = null;
        //this.nombre = null;
        //this.apellido = null;
    }

    public Alumno(String nombre, String apellido) {
        //this.id = null;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Alumno(int id, String nombre, String apellido) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Override
    public String toString() {
        return "AlumnoPOJO:{" + "id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + '}';
    }

}
