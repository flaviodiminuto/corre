package br.com.flavio.controllers;

import br.com.flavio.Repository.domain.SkillRepository;
import br.com.flavio.Repository.domain.simpler.SkillSimplerRepository;
import br.com.flavio.model.Skill;
import br.com.flavio.util.ControllerResponseUtil;
import desenvolvimento.tool.InitSkillsTable;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/skills")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ControllerSkill {

    private Log log = LogFactory.getLog(ControllerSkill.class);

    @Inject
    SkillRepository repository;
    @Inject
    InitSkillsTable initSkillsTable;
    @Inject
    SkillSimplerRepository simpler;

    @GET
    @Path("/suggestion/{nome}")
    public Response list(@PathParam("nome") String nome){
        try{
            return Response.status(Response.Status.FOUND)
                    .entity(simpler.listByNameSuggestion(nome))
                    .build();
        }catch (Exception e){
            String mensagem = "Falha ao listar Skills";
            log.fatal(mensagem + "  - suggestion Nome : List");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ControllerResponseUtil.getMessageErrorJSON(mensagem))
                    .build();
        }
    }
    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id){
        try {
            return Response.status(Response.Status.FOUND)
                    .type(MediaType.APPLICATION_JSON)
                    .entity(repository.findById(id))
                    .build();
        }catch (NumberFormatException nfe){
            String mensagem = "O [id] informado não é um número";
            log.fatal(mensagem,nfe);
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity(ControllerResponseUtil.getMessageErrorJSON(mensagem))
                    .build();
        }catch (Exception e) {
            String mensagem = "Não foi possivel criar skill";
            log.fatal(mensagem);
            return Response.status(Response.Status.NOT_FOUND)
                    .type(MediaType.APPLICATION_JSON)
                    .entity(ControllerResponseUtil.getMessageErrorJSON(mensagem))
                    .build();
        }
    }

    @GET
    public Response list(){
        try{
            return Response.status(Response.Status.FOUND)
                    .entity(simpler.list("deletado = false order by nome"))
                    .build();
        }catch (Exception e){
            String mensagem = "Falha ao listar Skills";
            log.fatal(mensagem + " : List");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ControllerResponseUtil.getMessageErrorJSON(mensagem))
                    .build();
        }
    }

    @POST
    @Path("/init")
    public Response init(){
        try{
            return Response.status(Response.Status.CREATED)
                .entity(initSkillsTable.initTable())
                .build();
        }catch (Exception e){
            String mensagem = "Falha ao listar Skills";
            log.fatal(mensagem + " : List");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ControllerResponseUtil.getMessageErrorJSON(mensagem))
                    .build();
        }
    }

    @POST
    public Response create(Skill skill){
        try {
            return Response.status(Response.Status.CREATED)
                    .entity(repository.create(skill))
                    .build();
        }catch (Exception e){
            log.fatal("Falha ao persistir skill ",e);
        }
        return Response.status(Response.Status.NOT_ACCEPTABLE)
                .entity(ControllerResponseUtil.getMessageErrorJSON("Não foi possivel criar Skill"))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    @PUT
    public Response atualiza(Skill skill){
        try {
            repository.update(skill);
            return Response.status(Response.Status.OK)
                    .entity(skill)
                    .build();
        }catch (Exception e){
            String mensagem = "Falha ao atualizar skill";
            log.fatal(mensagem + " ControllerSkill : atualiza");
            return Response.status(Response.Status.NOT_MODIFIED)
                    .entity(ControllerResponseUtil.getMessageErrorJSON(mensagem))
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") String id){
        try {
            Long idLong = Long.parseLong(id);
            repository.deletarSkill(idLong);
            return Response.status(Response.Status.OK)
                    .entity(ControllerResponseUtil.getMessageResponseTextJSON("Skill deletada"))
                    .build();
        }catch (NumberFormatException nfe){
            String mensagem = "Falha ao deletar, id informado não é um número";
            log.fatal(mensagem,nfe);
           return  Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity(ControllerResponseUtil.getMessageErrorJSON(mensagem))
                    .type(MediaType.APPLICATION_JSON)
                    .build();

        }catch (Exception e){
            String mensagem = "Falha ao deletar a Skill de Id "+id;
            log.fatal(mensagem,e);
            return  Response.status(Response.Status.NOT_MODIFIED)
                    .entity(ControllerResponseUtil.getMessageErrorJSON(mensagem))
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }
}
