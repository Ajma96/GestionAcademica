/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvdc.acceso.pojo;

/**
 *
 * @author profesor
 */
public class AluAsi {

    /*  ASIGNATURA INT,
	ALUMNO INT,
        FALTAS INT,
	PRIMARY KEY(ASIGNATURA,ALUMNO),
	FOREIGN KEY (ALUMNO) REFERENCES ALUMNO(id) ,
	FOREIGN KEY (ASIGNATURA) REFERENCES ASIGNATURA(id) );*/
    private int asignatura;
    private int alumno;
    private int faltas;

    public AluAsi() {

    }

    public AluAsi(int asignatura, int alumno, int faltas) {
        this.asignatura = asignatura;
        this.alumno = alumno;
        this.faltas = faltas;
    }

    public int getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(int asignatura) {
        this.asignatura = asignatura;
    }

    public int getAlumno() {
        return alumno;
    }

    public void setAlumno(int alumno) {
        this.alumno = alumno;
    }

    public int getFaltas() {
        return faltas;
    }

    public void setFaltas(int faltas) {
        this.faltas = faltas;
    }

}
