package br.com.flavio.controllers;

import br.com.flavio.model.Contato;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/contatos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ControllerContato {

    private Contato contato;

    //Create
    @POST
    public Contato criar(Contato a){
        setContato(a);
        return a;
    }

    //Read
    @GET
    public Contato get(){
        return this.getContato();
    }

    //Update
    @PUT
    public Contato update(Contato contato){
        setContato(contato);
        return getContato();
    }

    //Delete
    //@DELETE


    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }
}
