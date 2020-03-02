package br.com.flavio.controllers;

import br.com.flavio.Repository.domain.UsuarioRepository;
import br.com.flavio.Repository.domain.simpler.UsuarioSimplerRepository;
import br.com.flavio.model.Usuario;
import br.com.flavio.model.simpler.UsuarioSimpler;
import br.com.flavio.util.ControllerResponseUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.inject.Inject;
import javax.persistence.TransactionRequiredException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ControllerUsuario {

    @Inject
    UsuarioRepository repository;
    @Inject
    UsuarioSimplerRepository simpler;
    private Log log = LogFactory.getLog(ControllerUsuario.class);

    //GET
    @GET
    public Response list(){
        List<UsuarioSimpler> users = new ArrayList<>();
        try {
            users.addAll(simpler.list("deletado = false order by login"));
        }catch (Exception e){
            String mensagem = "Falha ao listar Usuarios";
            log.fatal(mensagem + " UsuarioSimplerRepository List",e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ControllerResponseUtil.getMessageErrorJSON(mensagem))
                    .build();
        }
        if(users.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ControllerResponseUtil.getMessageResponseTextJSON("Sem registros encontrados"))
                    .build();
        }else{
            return Response.status(Response.Status.FOUND)
                    .entity(users)
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id){
        Usuario usuario = repository.findById(id);
        if(usuario != null)
            return Response.status(Response.Status.FOUND)
                .entity(usuario)
                .build();
        else
            return Response.status(Response.Status.NOT_FOUND)
                .entity(ControllerResponseUtil.getMessageResponseTextJSON("Registro não encontrado"))
                .build();
    }

    @POST
    @Transactional
    public Response create(Usuario usuario){
        try {
            if(usuario == null ) throw new TransactionRequiredException(" Usuario não informado : NULL");
            repository.persist(usuario);
            return Response.status(Response.Status.CREATED)
                    .entity(usuario)
                    .build();
        }catch (TransactionRequiredException te) {
            String mensagem = "Requisitos não atendidos para registro de usuario";
            log.fatal(mensagem + "ControllerUsuario create\n",te);
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity(ControllerResponseUtil.getMessageErrorJSON(mensagem))
                    .build();
        }catch (Exception e){
            String mensagem = "Falha ao registrar usuário";
            log.fatal(mensagem,e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ControllerResponseUtil.getMessageErrorJSON(mensagem))
                    .build();
        }
    }

    //PUT
    @PUT
    public Response atualiza(Usuario usuario){
        String mensagem;
        try {
            if (usuario == null) throw new TransactionRequiredException(" Usuario não informado : NULL");
            repository.update(usuario);
            return Response.status(Response.Status.OK)
                    .entity(usuario)
                    .build();
        }catch (TransactionRequiredException tre){
            mensagem = "Falha ao atualizar  Usuario ";
            log.fatal(mensagem + " ControllerUsuario : atualiza",tre);
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity(ControllerResponseUtil.getMessageErrorJSON(mensagem))
                    .build();
        }
    }

    //DELETE
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id){
        String mensagem ;
        if( repository.delete(id) > 0){
            mensagem = "Usuario deletado ";
            log.info(mensagem);
            return Response.status(Response.Status.OK)
                    .entity(ControllerResponseUtil.getMessageResponseTextJSON(mensagem))
                    .build();
        }
        mensagem = "Delete não realizado";
        log.info(mensagem + " Sem falha");
        return Response.status(Response.Status.NOT_MODIFIED)
                .entity(ControllerResponseUtil.getMessageResponseTextJSON(mensagem))
                .build();
    }
}
