package br.com.flavio.controllers;

import br.com.flavio.Repository.SkillRepository;
import br.com.flavio.model.Skill;
import desenvolvimento.tool.InitSkillsTable;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/skills")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ControllerSkill {

    private Log log = LogFactory.getLog(ControllerSkill.class);

    @Inject
    SkillRepository skillRepository;
    @Inject
    InitSkillsTable initSkillsTable;

    @GET
    @Path("/{nome}")
    public List<Skill> list(@PathParam("nome") String nome){
        return skillRepository.listByNameSuggestion(nome);
    }

    @GET
    public List<Skill> list(){
        return skillRepository.list(" order by nome ");
    }

    @GET
    @Path("/{id}")
    public Skill findById(@PathParam("id") String id){
        try {
            Long idLong = Long.parseLong(id);
            return skillRepository.findById(idLong);
        }catch (NumberFormatException nfe){
            log.fatal("Falha ao procurar uma Skill",nfe);
            nfe.printStackTrace();
        }
        return skillRepository.findByName(String.valueOf(id));
    }

    @POST
    @Path("/init")
    public List<Skill> init(){
        return initSkillsTable.initTable();
    }

    @POST
    public Response create(Skill skill){
        try {
            skill.setId(null);
            return Response.status(Response.Status.OK)
                    .entity(skillRepository.create(skill))
                    .build();
        }catch (Exception e){
            log.fatal("Falha ao persistir skill ",e);
        }
        return Response.status(Response.Status.NOT_ACCEPTABLE)
                .entity("{\"mensage-de-erro\": \"Não foi possivel criar skill\"}")
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    @PUT
    public Skill atualiza(Skill skill){
        return skillRepository.update(skill);
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") String id){
        try {
            Long idLong = Long.parseLong(id);
            skillRepository.deletarSkill(idLong);
            return Response.status(Response.Status.OK)
                    .entity("Skill deletada")
                    .build();
        }catch (NumberFormatException nfe){
            String mensagem = "Falha ao deletar, id informado não é um número";
            log.fatal(mensagem,nfe);
           return  Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity(String.format("{\"mensagem-de-erro\":\"%s\"}",mensagem))
                    .type(MediaType.APPLICATION_JSON)
                    .build();

        }catch (Exception e){
            String mensagem = "Falha ao deletar a Skill de Id "+id;
            log.fatal(mensagem,e);
            return  Response.status(Response.Status.NOT_MODIFIED)
                    .entity(String.format("{\"mensagem-de-erro\":\"%s\"}",mensagem))
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }

}
