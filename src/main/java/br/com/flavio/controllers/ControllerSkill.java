package br.com.flavio.controllers;

import br.com.flavio.Repository.SkillRepository;
import br.com.flavio.model.Skill;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/skill")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ControllerSkill {

    @Inject
    SkillRepository skillRepository;

    @GET
    @Path("/list")
    public List<Skill> list(){
        return skillRepository.findByNameSuggestion("ava");
    }
    @GET
    @Path("/find")
    public Skill find(){
        Skill retorno =  skillRepository.findByName("Java");
        return retorno;
    }
}
