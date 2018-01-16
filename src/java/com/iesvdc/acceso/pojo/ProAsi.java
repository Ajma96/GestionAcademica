/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvdc.acceso.pojo;

import java.util.Date;

/**
 *
 * @author profesor
 */
public class ProAsi {
    
    private int asignatura;
    private int profesor;
    private Date inicio;

    public ProAsi() {
        
    }

    public ProAsi(int asignatura, int profesor, Date inicio) {
        this.asignatura = asignatura;
        this.profesor = profesor;
        this.inicio = inicio;
    }

    public int getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(int asignatura) {
        this.asignatura = asignatura;
    }

    public int getProfesor() {
        return profesor;
    }

    public void setProfesor(int profesor) {
        this.profesor = profesor;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    
    
}
