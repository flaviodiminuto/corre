package br.com.flavio.controllers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/materia")
public class ControllerMateria {

    @GET
    public String hello(){
        return "Hello Materia";
    }
}
