/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvdc.acceso.dao;

import com.iesvdc.acceso.pojo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alex
 */
public class UsuarioDAOImpl implements UsuarioDAO {
    
    Conexion conex;

    public UsuarioDAOImpl(){}
     
    private Connection obtenerConexion() throws DAOException {
        if (conex == null) {
            conex = new Conexion();
        }
        return conex.getConexion();
    }

    @Override
    public void create(Usuario us) throws DAOException {
        try {
            if (us.getUsername().length() > 3 && us.getPassword().length() > 6 &&
                       (us.getTipo() > -2 && us.getTipo() < 2 || us.getTipo() == 0)) {
                Connection con = obtenerConexion();
                PreparedStatement pstm = con.prepareStatement("INSERT INTO USUARIO VALUES(?,?,?)");
                pstm.setString(1, us.getUsername());
                pstm.setInt(3, us.getTipo());
                pstm.setString(2, us.getPassword());
                pstm.execute();
                con.close();
            } else {
                throw new DAOException("Usuario:Crear: El nombre debe ser de al " + 
                        "menos 4 caracteres, la contraseña mayor de 7 y" + 
                        " los tipos soportados son \"-1\", \"0\" y \"1\"");
            }
        } catch (SQLException ex) {
            throw new DAOException("Usuario:Crear: No puedo conectar a la BBDD");
        } 
    }
    
    @Override
    public void update(String old_username, Usuario new_us) throws DAOException {
       try {
            Connection con = obtenerConexion();
            PreparedStatement pstm = con.prepareStatement(" UPDATE USUARIO SET USERNAME=?, PASSWORD=?, TIPO=? WHERE USERNAME=?");
            pstm.setString(1, new_us.getUsername());
            pstm.setString(2, new_us.getPassword());
            pstm.setInt(3, new_us.getTipo());
            pstm.setString(4, old_username);
            pstm.execute();
            con.close();
        } catch (SQLException ex) {
            throw new DAOException("Usuario:Update: No puedo conectar a la BBDD");
        }

    }

    @Override
    public void update(Usuario old_us, Usuario new_us) throws DAOException {
        update(old_us.getUsername(), new_us);
    }

    @Override
    public void delete(String username) throws DAOException {
        try {
            Connection con = obtenerConexion();
            PreparedStatement pstm = con.prepareStatement("DELETE FROM USUARIO WHERE USERNAME=?");
            pstm.setString(1, username);
            pstm.execute();
            con.close();
        } catch (SQLException ex) {
            throw new DAOException("Usuario:Delete: No puedo conectar a la BBDD");
        }   
    }

    @Override
    public void delete(Usuario us) throws DAOException {
        delete(us.getUsername());
    }

    @Override
    public Usuario findByUsername(String username) throws DAOException {
        Usuario user = null;
        try {
            Connection con = obtenerConexion();
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM USUARIO WHERE USERNAME=?");
            pstm.setString(1, username);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                user = new Usuario(rs.getString("USERNAME"), rs.getString("PASSWORD"), rs.getInt("TIPO"));
            }
            con.close();
        } catch (SQLException ex) {
            throw new DAOException("Usuario:findByUsername: No puedo conectar a la BBDD ");
        }
        return user;
    }


    @Override
    public List<Usuario> findByType(Integer tipo) throws DAOException {
        Usuario user;
        List<Usuario> users_list= new ArrayList<>();
        try {
            Connection con = obtenerConexion();
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM USUARIO WHERE TIPO=?");
            pstm.setInt(1, tipo);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                user = new Usuario(rs.getString("USERNAME"), rs.getString("PASSWORD"), rs.getInt("TIPO"));
                users_list.add(user);
            }
            con.close();
        } catch (SQLException ex) {
            throw new DAOException("Usuario:findByType: No puedo conectar a la BBDD ");
        }
        return users_list;
    }

    @Override
    public List<Usuario> findAll() throws DAOException {
        Usuario user;
        List<Usuario> users_list = new ArrayList<>();
        try {
            Connection con = obtenerConexion();
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM USUARIO");
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                user = new Usuario(rs.getString("USERNAME"), rs.getString("PASSWORD"), rs.getInt("TIPO"));
                users_list.add(user);
            }
            con.close();
        } catch (SQLException ex) {
            throw new DAOException("Usuario:findAll: No puedo conectar a la BBDD ");
        }
        return users_list;
    }
    
}
