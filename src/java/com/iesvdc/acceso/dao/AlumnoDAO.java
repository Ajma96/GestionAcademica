/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvdc.acceso.dao;

import com.iesvdc.acceso.pojo.Alumno;
import java.util.List;

/**
 *
 * @author profesor
 */
public interface AlumnoDAO {

    public void create(Alumno al) throws DAOException;

    public void update(Alumno old_al, Alumno new_al) throws DAOException;

    public void update(Integer old_id, Alumno new_al) throws DAOException;

    public void delete(Integer id) throws DAOException;

    public void delete(Alumno al) throws DAOException;

    public Alumno findById(Integer Id) throws DAOException;

    public List<Alumno> findByNombre(String nombre) throws DAOException;

    public List<Alumno> findByApellido(String apellido) throws DAOException;

    public List<Alumno> findAll() throws DAOException;

    public List<Alumno> findByNombreApellido(String nombre, String apellido) throws DAOException;
}
