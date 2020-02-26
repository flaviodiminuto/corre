package br.com.flavio.controllers;

import br.com.flavio.model.Skill;
import desenvolvimento.tool.InitSkillsTable;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/init")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class Init {
    @Inject InitSkillsTable initSkillsTable;

    @POST
    public List<Skill> init(){
        return initSkillsTable.initTable();
    }
}
