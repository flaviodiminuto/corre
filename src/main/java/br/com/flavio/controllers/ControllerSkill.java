package br.com.flavio.controllers;

import br.com.flavio.Repository.SkillRepository;
import br.com.flavio.model.Skill;
import desenvolvimento.tool.InitSkillsTable;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/skill")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ControllerSkill {

    @Inject
    SkillRepository skillRepository;
    @Inject
    InitSkillsTable initSkillsTable;

    @POST
    @Path("/init")
    public List<Skill> init(){
        return initSkillsTable.initTable();
    }

    @POST
    @Path("/create")
    public Skill create(Skill skill){
        return skillRepository.create(skill);
    }

    @GET
    @Path("/list/{nome}")
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Skill> list(@PathParam("nome") String nome){
        return skillRepository.listByNameSuggestion(nome);
    }
    @GET
    @Path("/find")
    public Skill find(Long id){
        return skillRepository.findByName(String.valueOf(id));
    }

    @DELETE
    @Path("/delete")
    public int delete(Long id){
        return skillRepository.deletarSkill(id);
    }

}
