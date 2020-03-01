package br.com.flavio.controllers;

import br.com.flavio.Repository.domain.SkillRepository;
import br.com.flavio.Repository.domain.simpler.SkillSimplerRepository;
import br.com.flavio.model.Skill;
import br.com.flavio.model.simpler.SkillSimpler;
import desenvolvimento.tool.InitSkillsTable;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
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
    @Inject
    SkillSimplerRepository simpler;

    @GET
    @Path("/suggestion/{nome}")
    public List<SkillSimpler> list(@PathParam("nome") String nome){
        return simpler.listByNameSuggestion(nome);
    }
    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id){
        try {
//            Long idLong = Long.parseLong(id);
            return Response.status(Response.Status.OK)
                    .type(MediaType.APPLICATION_JSON)
                    .entity(skillRepository.findById(id))
                    .build();
        }catch (NumberFormatException nfe){
            log.fatal("Falha ao procurar uma Skill",nfe);
            nfe.printStackTrace();
        }
        return Response.status(Response.Status.NOT_FOUND)
                .type(MediaType.APPLICATION_JSON)
                .entity(getMessageErrorJson("Não foi possivel criar skill"))
                .build();
    }

    @GET
    public List<SkillSimpler> list(){
        return simpler.list(" order by nome ");
    }



//        return skillRepository.findByName(String.valueOf(id));

    @POST
    @Path("/init")
    public List<Skill> init(){
        return initSkillsTable.initTable();
    }

    @POST
    public Response create(Skill skill){
        try {
            skill.setId(null);
            skill.setDataCriacao(new Date());
            skill.setDataAtualizacao(null);
            skill.setDeletado(false);
            return Response.status(Response.Status.OK)
                    .entity(skillRepository.create(skill))
                    .build();
        }catch (Exception e){
            log.fatal("Falha ao persistir skill ",e);
        }
        return Response.status(Response.Status.NOT_ACCEPTABLE)
                .entity(getMessageErrorJson("Não foi possivel criar Skill"))
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
                    .entity("{\"resonse-text\":\"Skill deletada\"}")
                    .build();
        }catch (NumberFormatException nfe){
            String mensagem = "Falha ao deletar, id informado não é um número";
            log.fatal(mensagem,nfe);
           return  Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity(getMessageErrorJson(mensagem))
                    .type(MediaType.APPLICATION_JSON)
                    .build();

        }catch (Exception e){
            String mensagem = "Falha ao deletar a Skill de Id "+id;
            log.fatal(mensagem,e);
            return  Response.status(Response.Status.NOT_MODIFIED)
                    .entity(getMessageErrorJson(mensagem))
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }

    private String getMessageErrorJson(String mensagem){
        return String.format("{\"mensage-de-erro\": \"%s\"}",mensagem);
    }
}
