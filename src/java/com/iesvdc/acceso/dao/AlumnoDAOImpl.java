/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvdc.acceso.dao;

import com.iesvdc.acceso.pojo.Alumno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import java.util.logging.Level;
//import java.util.logging.Logger;

/**
 *
 * @author profesor
 */
public class AlumnoDAOImpl implements AlumnoDAO {

    public AlumnoDAOImpl() {
    }

    Conexion conex;

    private Connection obtenerConexion() throws DAOException {
        if (conex == null) {
            conex = new Conexion();
        }
        return conex.getConexion();
    }

    @Override
    public void create(Alumno al) throws DAOException {
        try {
            if (al.getApellido().length() >= 3 && al.getNombre().length() > 1) {
                Connection con = obtenerConexion();
                PreparedStatement pstm = con.prepareStatement("INSERT INTO ALUMNO VALUES(NULL, ?,?)");
                pstm.setString(1, al.getNombre());
                pstm.setString(2, al.getApellido());
                pstm.execute();
                con.close();
            } else {
                throw new DAOException("Alumno:Crear: El nombre es demasiado corto");
            }
        } catch (SQLException ex) {
            throw new DAOException("Alumno:Crear: No puedo conectar a la BBDD");
        }
    }

    @Override
    public void update(Alumno old_al, Alumno new_al) throws DAOException {
        update(old_al.getId(), new_al);
    }

    @Override
    public void update(Integer old_id, Alumno new_al) throws DAOException {
        try {
            Connection con = obtenerConexion();
            PreparedStatement pstm = con.prepareStatement(" UPDATE ALUMNO SET ID=?, NOMBRE=?, APELLIDO=? WHERE ID=?");
            pstm.setInt(4, old_id);
            pstm.setInt(1, new_al.getId());
            pstm.setString(2, new_al.getNombre());
            pstm.setString(3, new_al.getApellido());
            pstm.execute();
            con.close();
        } catch (SQLException ex) {
            throw new DAOException("Alumno:Update: No puedo conectar a la BBDD");
        }
    }

    @Override
    public void delete(Integer id) throws DAOException {
        try {
            Connection con = obtenerConexion();
            PreparedStatement pstm = con.prepareStatement("DELETE FROM ALUMNO WHERE id=?");
            pstm.setInt(1, id);
            pstm.execute();
            con.close();
        } catch (SQLException ex) {
            throw new DAOException("Alumno:Delete: No puedo conectar a la BBDD");
        }
    }

    @Override
    public void delete(Alumno al) throws DAOException {
        delete(al.getId());
    }

    @Override
    public Alumno findById(Integer Id) throws DAOException {
        Alumno al;
        try {
            Connection con = obtenerConexion();
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM ALUMNO WHERE id=?");
            pstm.setInt(1, Id);
            ResultSet rs = pstm.executeQuery();
            rs.next();
            al = new Alumno(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellido"));
            con.close();
        } catch (SQLException ex) {
            al = new Alumno(-1, "error", "error");
            throw new DAOException("Alumno:findById: No puedo conectar a la BBDD ");
        }
        return al;
    }

    @Override
    public List<Alumno> findByNombre(String nombre) throws DAOException {
        Alumno al;
        List<Alumno> list_al = new ArrayList<>();
        try {
            Connection con = obtenerConexion();
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM ALUMNO WHERE nombre=?");
            pstm.setString(1, nombre);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                al = new Alumno(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellido"));
                list_al.add(al);
            }
            con.close();
        } catch (SQLException ex) {
            throw new DAOException("Alumno:findByNombre: No puedo conectar a la BBDD ");
        }
        return list_al;
    }

    @Override
    public List<Alumno> findByApellido(String apellido) throws DAOException {
        Alumno al;
        List<Alumno> list_al = new ArrayList<>();
        try {
            Connection con = obtenerConexion();
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM ALUMNO WHERE apellido=?");
            pstm.setString(1, apellido);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                al = new Alumno(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellido"));
                list_al.add(al);
            }
            con.close();
        } catch (SQLException ex) {
            throw new DAOException("Alumno:findByApellido: No puedo conectar a la BBDD ");
        }
        return list_al;
    }

    @Override
    public List<Alumno> findByNombreApellido(String nombre, String apellido) throws DAOException {
        Alumno al;
        List<Alumno> list_al = new ArrayList<>();
        try {
            Connection con = obtenerConexion();
            PreparedStatement pstm = con.prepareStatement(
                    "SELECT * FROM ALUMNO WHERE nombre=? AND apellido=?");
            pstm.setString(1, nombre);
            pstm.setString(2, apellido);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                al = new Alumno(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellido"));
                list_al.add(al);
            }
            con.close();
        } catch (SQLException ex) {
            throw new DAOException("Alumno:findByApellido: No puedo conectar a la BBDD ");
        }
        return list_al;
    }

    @Override
    public List<Alumno> findAll() throws DAOException {
        Alumno al;
        List<Alumno> list_al = new ArrayList<>();
        try {
            Connection con = obtenerConexion();
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM ALUMNO");
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                al = new Alumno(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellido"));
                list_al.add(al);
            }
            con.close();
        } catch (SQLException ex) {
            throw new DAOException("Alumno:findByApellido: No puedo conectar a la BBDD ");
        }
        return list_al;
    }

}
