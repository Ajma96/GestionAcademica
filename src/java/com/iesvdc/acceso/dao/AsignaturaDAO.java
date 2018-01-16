/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvdc.acceso.dao;

import com.iesvdc.acceso.pojo.AluAsi;
import com.iesvdc.acceso.pojo.ProAsi;
import com.iesvdc.acceso.pojo.Alumno;
import com.iesvdc.acceso.pojo.Asignatura;
import com.iesvdc.acceso.pojo.Profesor;
import java.util.List;

/**
 *
 * @author profesor
 */
public interface AsignaturaDAO {

    void create(Asignatura as) throws DAOException;
    
    void update(Asignatura old_asig, Asignatura new_asig) throws DAOException;

    void update(int old_id, Asignatura new_asig) throws DAOException;
    
    void delete(int id) throws DAOException;
    
    void delete(Asignatura asig) throws DAOException;

    List<Asignatura> findAll() throws DAOException;

    List<Asignatura> findByName(String name) throws DAOException;

    Asignatura findById(Integer id) throws DAOException;

    List<Asignatura> findByCurso(Integer curso) throws DAOException;

    List<Asignatura> findByCiclo(String ciclo) throws DAOException;

    List<Alumno> findAlumnos(Integer id_as) throws DAOException;

    List<Alumno> findAlumnos(Asignatura as) throws DAOException;

    List<Profesor> findProfesores(Integer id_as) throws DAOException;

    List<Profesor> findProfesores(Asignatura as) throws DAOException;

    List<AluAsi> findAluAsi(Integer id_as) throws DAOException;

    List<AluAsi> findAluAsi(Asignatura as) throws DAOException;

    List<ProAsi> findProAsi(Integer id_as) throws DAOException;

    List<ProAsi> findProAsi(Asignatura as) throws DAOException;
}
