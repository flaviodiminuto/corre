package br.com.flavio.controllers;

import br.com.flavio.Repository.domain.UsuarioRepository;
import br.com.flavio.enumeradores.CategoriaUsuario;
import br.com.flavio.model.Sessao;
import br.com.flavio.model.usuario.Usuario;
import br.com.flavio.model.usuario.UsuarioBuilder;
import br.com.flavio.model.usuario.UsuarioCadastro;
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

    private static Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @POST
    @Transactional
    public Response post(UsuarioCadastro usuarioCadastro){
        Usuario usuario = new UsuarioBuilder()
                .login(usuarioCadastro.getLogin())
                .senha(usuarioCadastro.getSenha())
                .dataCadastro(new Date())
                .dataAtualizacao(new Date())
                .categoriaUsuario(CategoriaUsuario.USUARIO)
                .build();
        if(repository.autenticar(usuarioCadastro.getLogin(),usuarioCadastro.getSenha())!= null)
           return Response.status(Response.Status.NOT_ACCEPTABLE).entity("Usuário já existe").build();
        repository.persist(usuario);
        return Response.status(Response.Status.OK).entity(usuario).build();
    }

    //put
    @PUT
    public Usuario put(Usuario usuario){
        repository.update(usuario);
        return usuario;
    }
    //delete - by Id
    @DELETE
    public void delete(Usuario usuario){
        repository.delete(usuario.getId());
    }
    //get - list
    @GET
    public List<Usuario> list(){
        return repository.findAll().list();
    }
    //get - by id
    @GET
    @Path("/{id}")
    public Usuario byId(@PathParam("id") Long id){
        return repository.findById(id);
    }

    @POST
    @Path("/{login}/{senha}")
    public Response autenticar(@PathParam("login") String login,@PathParam("senha") String senha){
        Response response;
        String message;
        try {
            Optional<Usuario> optional = repository.autenticar(login, senha);
            Sessao sessao = new Sessao(optional);
            if (optional.isPresent()) {
                response = Response.status(Response.Status.OK).entity(sessao).build();
                message = "Acesso autorizado : " + sessao.getUsuario().getLogin();
                logger.info(message);
            } else {
                response = Response.status(Response.Status.NOT_FOUND).entity(sessao).build();
                message = "Acesso Negado : " + login;
                logger.warn(message);
            }
        }catch (Exception e){
            message = "Falha durante autenticacao : " + login;
            Map<String,String> error = new HashMap<>();
            error.put("error", message );
            response =  Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
            logger.error(message);
        }
        return  response;
    }
}
