/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package generic;

import javax.ws.rs.*;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import utils.Result;
import utils.transform.Render;

/**
 *
 * @author itakenami
 */
public abstract class GenericRest2<E> {
    
    
    public abstract GenericModel2 getModel();
    public abstract E getDtoToSave(MultivaluedMap<String, String> form);
    public abstract void setDtoToSave(E dto, MultivaluedMap<String, String> form);
    
    @Path("/")
    @GET
    public Response listAll() {
        try {
            return Response.ok(Render.JSON(Result.OK(getModel().findAll()))).type("application/json").header("Access-Control-Allow-Origin", "*").build();
        } catch (Exception ex) {
            return Response.ok(Render.JSON(Result.SYSERROR(ex.getMessage()))).type("application/json").header("Access-Control-Allow-Origin", "*").build();
        }
    }

    @Path("/{id}")
    @GET
    public Response findId(@PathParam("id") long id) {

        E obj = (E) getModel().findById(id);

        if (obj == null) {
            //Registro nao encontrado
            return Response.ok(Render.JSON(Result.ERROR(3))).type("application/json").header("Access-Control-Allow-Origin", "*").build();
        }

        return Response.ok(Render.JSON(Result.OK(obj))).type("application/json").header("Access-Control-Allow-Origin", "*").build();
    }
    
    @Path("/delete/{id}")
    @GET
    public Response deleteId(@PathParam("id") long id) {

        try {
            if(getModel().delete(id)){
                return Response.ok(Render.JSON(Result.OK("Cargo apagado com sucesso."))).type("application/json").header("Access-Control-Allow-Origin", "*").build();
            }else{
                return Response.ok(Render.JSON(Result.SYSERROR("Não foi possivel exlcuir."))).type("application/json").header("Access-Control-Allow-Origin", "*").build();
            }
        } catch (Exception ex) {
            return Response.ok(Render.JSON(Result.SYSERROR(ex.getMessage()))).type("application/json").header("Access-Control-Allow-Origin", "*").build();
        }
    }
    
    @Path("/")
    @POST
    public Response save(MultivaluedMap<String, String> form) {
        
        E obj = getDtoToSave(form);
        
        try {
            getModel().save(obj);
            return Response.ok(Render.JSON(Result.OK(obj))).type("application/json").header("Access-Control-Allow-Origin", "*").build();
        } catch (Exception ex) {
            return Response.ok(Render.JSON(Result.SYSERROR(ex.getMessage()))).type("application/json").header("Access-Control-Allow-Origin", "*").build();
        }
    }
    
    @Path("/{id}")
    @POST
    public Response update2(@PathParam("id") long id, MultivaluedMap<String, String> form) {

        E obj = (E) getModel().findById(id);

        if (obj == null) {
            //Registro nao encontrado
            return Response.ok(Render.JSON(Result.ERROR(3))).type("application/json").header("Access-Control-Allow-Origin", "*").build();
        }

        setDtoToSave(obj, form);

        try {
            getModel().update(obj);
            return Response.ok(Render.JSON(Result.OK(obj))).type("application/json").header("Access-Control-Allow-Origin", "*").build();
        } catch (Exception ex) {
            return Response.ok(Render.JSON(Result.SYSERROR(ex.getMessage()))).type("application/json").header("Access-Control-Allow-Origin", "*").build();
        }
    }
    
    @Path("/{id}")
    @PUT
    public Response update(@PathParam("id") long id, MultivaluedMap<String, String> form) {

        E obj = (E) getModel().findById(id);

        if (obj == null) {
            //Registro nao encontrado
            return Response.ok(Render.JSON(Result.ERROR(3))).type("application/json").header("Access-Control-Allow-Origin", "*").build();
        }

        setDtoToSave(obj, form);

        try {
            getModel().update(obj);
            return Response.ok(Render.JSON(Result.OK(obj))).type("application/json").header("Access-Control-Allow-Origin", "*").build();
        } catch (Exception ex) {
            return Response.ok(Render.JSON(Result.SYSERROR(ex.getMessage()))).type("application/json").header("Access-Control-Allow-Origin", "*").build();
        }
    }

    @Path("/{id}")
    @DELETE
    public Response delete(@PathParam("id") long id) {

        try {
            if(getModel().delete(id)){
                return Response.ok(Render.JSON(Result.OK("Cargo apagado com sucesso."))).type("application/json").header("Access-Control-Allow-Origin", "*").build();
            }else{
                return Response.ok(Render.JSON(Result.SYSERROR("Não foi possivel exlcuir."))).type("application/json").header("Access-Control-Allow-Origin", "*").build();
            }
        } catch (Exception ex) {
            return Response.ok(Render.JSON(Result.SYSERROR(ex.getMessage()))).type("application/json").header("Access-Control-Allow-Origin", "*").build();
        }
    }
}
