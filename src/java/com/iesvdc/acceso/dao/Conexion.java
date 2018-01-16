/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvdc.acceso.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * jdbc:DRIVER://HOST:PORT/DATABASE_NAME y usuario y contraseña
 *
 * @author profesor
 */
public class Conexion {

    private Properties props;
    private Integer driver;
    private String host;
    private String puerto;
    private String base_datos;
    private String usuario;
    private String password;

    public static final int ORACLE = 1;
    public static final int MYSQL = 2;
    public static final int DERBY = 3;
    public static final int POSTGRE = 4;

    public Conexion() throws DAOException {

        // try (InputStream in =new FileInputStream(new File("/home/profesor/usr/apache-tomcat-8.5.23/webapps/GestionAcademica/WEB-INF/lib/db.prop"))  ) {
        // try (InputStream in = classLoader.getResourceAsStream("/WEB-INF/lib/db.prop")){        
        try (InputStream in = getClass().getClassLoader().getResourceAsStream("db.prop")) {
            // try (InputStream in = Files.newInputStream(FileSystems.getDefault().getPath(System.getProperty("catalina.base")+ File.separator + "/webapps/GestionAcademica/WEB-INF/lib/db.prop")); ) {
            props = new Properties();
            if (in != null) {
                props.load(in);
            } else {
                throw new DAOException("Conexion: No puedo cargar el archivo de configuracion NULL");
            }
            in.close();
        } catch (IOException ex) {
            throw new DAOException("Conexion: No puedo cargar el archivo de configuracion");
            // driver=-1;
        }
        loadProperties();
    }

    public Connection getConexion() throws DAOException {
        Connection con = null;
        String jdbcUrl = null;
        try {
            switch (driver) {
                case ORACLE:
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    jdbcUrl = "jdbc:oracle:thin:@" + this.host + ":"
                            + this.puerto + ":" + this.base_datos;
                    break;
                case MYSQL:
                    Class.forName("com.mysql.jdbc.Driver");
                    jdbcUrl = "jdbc:mysql://" + this.host + ":"
                            + this.puerto + "/" + this.base_datos;
                    break;
                default:
                    throw new DAOException("Conexion: El driver seleccionado no está disponible");
            }
            con = DriverManager.getConnection(jdbcUrl, usuario, password);
        } catch (SQLException | ClassNotFoundException ex) {
            String msg = "Conexion: No se puede conectar a la BBDD \n" + ex.getLocalizedMessage();
            throw new DAOException(msg);
        }

        return con;
    }

    /* 
    Conexion(int driver) throws DAOException {
        
        switch(driver){
            case ORACLE:
                
                break;
            case MYSQL:
                
                break;
            default:
                throw new DAOException("Conexion: El driver seleccionado no está disponible");
        }
        
    }*/
    private void loadProperties() {
        this.base_datos = props.getProperty("base_datos");
        this.driver = Integer.parseInt(props.getProperty("driver"));
        this.host = props.getProperty("host");
        this.password = props.getProperty("password");
        this.usuario = props.getProperty("usuario");
        this.puerto = props.getProperty("puerto");
    }
}
