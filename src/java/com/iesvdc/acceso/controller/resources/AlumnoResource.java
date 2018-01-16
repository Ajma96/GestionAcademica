/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvdc.acceso.controller.resources;

import com.iesvdc.acceso.dao.AlumnoDAO;
import com.iesvdc.acceso.dao.AlumnoDAOImpl;
import com.iesvdc.acceso.dao.DAOException;
import com.iesvdc.acceso.pojo.Alumno;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Juangu <jgutierrez at iesvirgendelcarmen.coms>
 */
@Path("/")
public class AlumnoResource {

    @GET
    @Path("alumno")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Alumno> getAlumnos() {
        AlumnoDAO al_dao = new AlumnoDAOImpl();
        List<Alumno> list_al;
        try {
            list_al = al_dao.findAll();
        } catch (DAOException ex) {
            list_al = new ArrayList<>();
            Logger.getLogger(ex.getLocalizedMessage());
        }
        return list_al;
    }

    @GET
    @Path("alumno/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Alumno getAlumnoById(@PathParam("id") String id) {
        AlumnoDAO al_dao = new AlumnoDAOImpl();
        Alumno al;
        try {
            al = al_dao.findById(Integer.parseInt(id));
        } catch (DAOException ex) {
            al = new Alumno(-1, "Error", "Error");
            Logger.getLogger(ex.getLocalizedMessage());
        }
        return al;
    }

    @GET
    @Path("alumno/apellido/{apellido}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Alumno> getAlumnoByApellido(@PathParam("apellido") String apellido) {
        AlumnoDAO al_dao = new AlumnoDAOImpl();
        List<Alumno> list_al;
        try {
            list_al = al_dao.findByApellido(apellido);
        } catch (DAOException ex) {
            list_al = new ArrayList<>();
            Logger.getLogger(ex.getLocalizedMessage());
        }
        return list_al;
    }

    @GET
    @Path("alumno/nombre/{nombre}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Alumno> getAlumnoByNombre(@PathParam("nombre") String nombre) {
        AlumnoDAO al_dao = new AlumnoDAOImpl();
        List<Alumno> list_al;
        try {
            list_al = al_dao.findByNombre(nombre);
        } catch (DAOException ex) {
            list_al = new ArrayList<>();
            Logger.getLogger(ex.getLocalizedMessage());
        }
        return list_al;
    }

    @GET
    @Path("alumno/nombre/{nombre}/apellido/{apellido}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Alumno> getAlumnoByNombreApellido(
            @PathParam("nombre") String nombre,
            @PathParam("apellido") String apellido) {
        AlumnoDAO al_dao = new AlumnoDAOImpl();
        List<Alumno> list_al;
        try {
            list_al = al_dao.findByNombreApellido(nombre, apellido);
        } catch (DAOException ex) {
            list_al = new ArrayList<>();
            Logger.getLogger(ex.getLocalizedMessage());
        }
        return list_al;
    }

    @PUT
    @Path("alumno/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void updateAlumno(@PathParam("id") Integer id, Alumno al) {
        AlumnoDAO al_dao = new AlumnoDAOImpl();
        try {
            al_dao.update(id, al);
        } catch (DAOException ex) {
            Logger.getLogger(ex.getLocalizedMessage());
        }
    }

    
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("alumno")
    public Response createAlumno(Alumno al) {
        AlumnoDAO al_dao = new AlumnoDAOImpl();
        try {
            al_dao.create(al);
        } catch (DAOException ex) {
            Logger.getLogger(ex.getLocalizedMessage());
            return Response.status(400).entity(al).build();
        }
        return Response.status(200).entity(al).build();
    }
    
    @DELETE
    @Path("alumno/{id}")
    public void deleteAlumno(@PathParam("id") Integer id) {
        AlumnoDAO al_dao = new AlumnoDAOImpl();
        try {
            al_dao.delete(id);
        } catch (DAOException ex) {
            Logger.getLogger(ex.getLocalizedMessage());
        }
    }
}
