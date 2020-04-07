package br.com.flavio.controllers;

import br.com.flavio.model.Sessao;
import br.com.flavio.model.usuario.UsuarioAlteracao;
import br.com.flavio.model.usuario.UsuarioRepository;
import br.com.flavio.enumeradores.CategoriaUsuario;
import br.com.flavio.model.usuario.Usuario;
import br.com.flavio.model.usuario.UsuarioBuilder;
import br.com.flavio.model.usuario.UsuarioCadastro;
import br.com.flavio.util.ControllerResponseUtil;
import io.quarkus.panache.common.Sort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;

@Path("/usuarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioController {
    @Inject
    UsuarioRepository repository;

    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @POST
    @Transactional
    public Response post(UsuarioCadastro usuarioCadastro){
        String mensagem;
        Usuario usuario = new UsuarioBuilder()
                .login(usuarioCadastro.getLogin())
                .senha(usuarioCadastro.getSenha())
                .dataCadastro(new Date())
                .dataAtualizacao(new Date())
                .categoriaUsuario(CategoriaUsuario.USUARIO)
                .build();
        if(repository.autenticar(usuarioCadastro.getLogin(),usuarioCadastro.getSenha()).isPresent()) {
            mensagem = String.format("Tentativa negada de cadastro repetido : login = %s",usuario.getLogin());
            logger.info(mensagem);
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity(ControllerResponseUtil.getMessageInfoTextJSON("Usuário já existe")).build();
        }
        repository.persist(usuario);
        mensagem  = String.format("Novo Usuario : login = %s, id = %d",usuario.getLogin(),usuario.getId());
        logger.info(mensagem);
        return Response.status(Response.Status.OK).entity(usuario).build();
    }

    //put
    @PUT
    public Response put(UsuarioAlteracao usuario) {
        String mensagem;
        Response response;
        switch (repository.update(usuario)) {
            case 0:// usuario inalterado
                mensagem = ControllerResponseUtil.getMessageInfoTextJSON("usuario não foi modificado");
                response =  Response.status(Response.Status.NOT_MODIFIED).entity(mensagem).build();
                break;
            case 1:// usuario atualizado
                mensagem = String.format("Usuario atualizado - Id = %d", usuario.getId());
                logger.info(mensagem);
                response =  Response.status(Response.Status.OK).entity(usuario).build();
                break;
            case 2:// usuario inexistente
                mensagem = ControllerResponseUtil.getMessageInfoTextJSON("Usuáiro não encontrado");
                response =  Response.status(Response.Status.NOT_FOUND).entity(mensagem).build();
                break;
            default:// falha ao atualizar
                mensagem = ControllerResponseUtil.getMessageErrorJSON("Falha ao atualizar usuário");
                response =  Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(mensagem).build();
                break;
        }
        return response;
    }

    //delete - by Id
    @DELETE
    public Response delete(UsuarioCadastro user){
        String mensagem;
        Optional<Usuario> optionalUsuario = repository.autenticar(user.getLogin(),user.getSenha());
        if(optionalUsuario.isPresent()) {
            // falha ao atualizar
            if (repository.delete(optionalUsuario.get().getId()) == 1) {// deletado alterado para TRUE
                mensagem = ControllerResponseUtil.getMessageInfoTextJSON("falha ao deletar usuário");
                logger.info(mensagem);
                return Response.status(Response.Status.OK).entity(mensagem).build();
            }
            mensagem = ControllerResponseUtil.getMessageErrorJSON("falha ao deletar usuário");
            logger.error(mensagem);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(mensagem).build();
        } else {
            mensagem = ControllerResponseUtil.getMessageInfoTextJSON("Usuario não encontrado");
            logger.info(mensagem);
            return Response.status(Response.Status.NOT_FOUND).entity(mensagem).build();
        }
    }

    //get - list
    @GET
    public List<Usuario> list(){
        String mensagem = ControllerResponseUtil.getMessageInfoTextJSON("Listando todos Usuários");
        logger.info(mensagem);
        return repository.listAll(Sort.by("id"));
    }

    //get - by id
    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id){
        String message;
        try {
            Optional<Usuario> optionalUsuario = repository.findOptionalById(id);
            if (optionalUsuario.isPresent()) {
                logger.info(String.format("Usuario pesquisado - Id = %d (fornecido)",optionalUsuario.get().getId()));
                return Response.status(Response.Status.OK).entity(optionalUsuario.get()).build();
            } else {
                message = ControllerResponseUtil.getMessageInfoTextJSON("Usuario não encontrado");
                logger.info(message);
                return Response.status(Response.Status.NOT_FOUND).entity(message).build();
            }
        }catch (Exception e){
            message = ControllerResponseUtil.getMessageErrorJSON("Falha ao pesquisar usuário");
            logger.error(message);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(message).build();
        }
    }

    @POST
    @Path("/{login}/{senha}")
    public Response autenticar(@PathParam("login") String login,@PathParam("senha") String senha){
        Response response;
        String mensagem;
        try {
            Optional<Usuario> optional = repository.autenticar(login, senha);
            if (optional.isPresent()) {
                Sessao sessao = new Sessao(optional);
                sessao.setAtiva(true);
                response = Response.status(Response.Status.OK).entity(sessao).build();
                mensagem = ControllerResponseUtil.getMessageInfoTextJSON("Acesso autorizado : " + sessao.getUsuario().getLogin());
            } else {
                mensagem = ControllerResponseUtil.getMessageInfoTextJSON("Acesso Negado : " + login);
                response = Response.status(Response.Status.NOT_FOUND).entity(mensagem).build();
            }
        }catch (Exception e){
            mensagem = ControllerResponseUtil.getMessageErrorJSON("Falha durante autenticacao : " + login);
            response =  Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(mensagem).build();
        }
        logger.error(mensagem);
        return  response;
    }
}
