/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvdc.acceso.controller.resources;

import com.iesvdc.acceso.dao.ProfesorDAO;
import com.iesvdc.acceso.dao.ProfesorDAOImpl;
import com.iesvdc.acceso.dao.DAOException;
import com.iesvdc.acceso.pojo.Profesor;
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
 * @author alex
 */
@Path("/")
public class ProfesorResource {

    @GET
    @Path("profesor")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Profesor> getProfesores() {
        ProfesorDAO prof_dao = new ProfesorDAOImpl();
        List<Profesor> list_prof;
        try {
            list_prof = prof_dao.findAll();
        } catch (DAOException ex) {
            list_prof = new ArrayList<>();
            Logger.getLogger(ex.getLocalizedMessage());
        }
        return list_prof;
    }

    @GET
    @Path("profesor/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Profesor getProfesorById(@PathParam("id") String id) {
        ProfesorDAO prof_dao = new ProfesorDAOImpl();
        Profesor prof;
        try {
            prof = prof_dao.findById(Integer.parseInt(id));
        } catch (DAOException ex) {
            prof = new Profesor(-1, "Error", "Error");
            Logger.getLogger(ex.getLocalizedMessage());
        }
        return prof;
    }

    @GET
    @Path("profesor/apellido/{apellido}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Profesor> getProfesorByApellido(@PathParam("apellido") String apellido) {
        ProfesorDAO prof_dao = new ProfesorDAOImpl();
        List<Profesor> list_prof;
        try {
            list_prof = prof_dao.findByApellido(apellido);
        } catch (DAOException ex) {
            list_prof = new ArrayList<>();
            Logger.getLogger(ex.getLocalizedMessage());
        }
        return list_prof;
    }

    @GET
    @Path("profesor/nombre/{nombre}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Profesor> getProfesorByNombre(@PathParam("nombre") String nombre) {
        ProfesorDAO prof_dao = new ProfesorDAOImpl();
        List<Profesor> list_prof;
        try {
            list_prof = prof_dao.findByNombre(nombre);
        } catch (DAOException ex) {
            list_prof = new ArrayList<>();
            Logger.getLogger(ex.getLocalizedMessage());
        }
        return list_prof;
    }

    @GET
    @Path("profesor/nombre/{nombre}/apellido/{apellido}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Profesor> getProfesorByNombreApellido(
            @PathParam("nombre") String nombre,
            @PathParam("apellido") String apellido) {
        ProfesorDAO prof_dao = new ProfesorDAOImpl();
        List<Profesor> list_prof;
        try {
            list_prof = prof_dao.findByNombreApellido(nombre, apellido);
        } catch (DAOException ex) {
            list_prof = new ArrayList<>();
            Logger.getLogger(ex.getLocalizedMessage());
        }
        return list_prof;
    }

    @PUT
    @Path("profesor/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void updateProfesor(@PathParam("id") Integer id, Profesor prof) {
        ProfesorDAO prof_dao = new ProfesorDAOImpl();
        try {
            prof_dao.update(id, prof);
        } catch (DAOException ex) {
            Logger.getLogger(ex.getLocalizedMessage());
        }
    }

    
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("profesor")
    public Response createProfesor(Profesor prof) {
        ProfesorDAO prof_dao = new ProfesorDAOImpl();
        try {
            prof_dao.create(prof);
        } catch (DAOException ex) {
            Logger.getLogger(ex.getLocalizedMessage());
            return Response.status(400).entity(prof).build();
        }
        return Response.status(200).entity(prof).build();
    }
    
    @DELETE
    @Path("profesor/{id}")
    public void deleteProfesor(@PathParam("id") Integer id) {
        ProfesorDAO prof_dao = new ProfesorDAOImpl();
        try {
            prof_dao.delete(id);
        } catch (DAOException ex) {
            Logger.getLogger(ex.getLocalizedMessage());
        }
    }
}
