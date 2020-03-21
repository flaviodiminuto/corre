package br.com.flavio.controllers;

import br.com.flavio.model.usuario.UsuarioAlteracao;
import br.com.flavio.repository.UsuarioRepository;
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
        Usuario usuario = new UsuarioBuilder()
                .login(usuarioCadastro.getLogin())
                .senha(usuarioCadastro.getSenha())
                .dataCadastro(new Date())
                .dataAtualizacao(new Date())
                .categoriaUsuario(CategoriaUsuario.USUARIO)
                .build();
        if(repository.autenticar(usuarioCadastro.getLogin(),usuarioCadastro.getSenha()).isEmpty())
           return Response.status(Response.Status.NOT_ACCEPTABLE).entity("Usuário já existe").build();
        repository.persist(usuario);
        return Response.status(Response.Status.OK).entity(usuario).build();
    }

    //put
    @PUT
    public Response put(UsuarioAlteracao usuario) {
        /* 0 - usuario inalterado
          1 - usuario atualizado
          2 - usuario inexistente
          3 ou mais - falha ao atualizar
         */
        switch (repository.update(usuario)) {
            case 0:
                return Response.status(Response.Status.NOT_MODIFIED).build();
            case 1:
                return Response.status(Response.Status.OK).entity(usuario).build();
            case 2:
                return Response.status(Response.Status.NOT_FOUND).build();
            default:
                Map<String,String> map = ControllerResponseUtil.mapResponse("erro", "falha ao atualizar usuário");
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(map).build();
        }
    }

    //delete - by Id
    @DELETE
    public void delete(Usuario usuario){
        repository.delete(usuario.getId());
    }
    //get - list
    @GET
    public List<Usuario> list(){
        return repository.listAll(Sort.by("id"));
    }
    //get - by id
    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id){
        try {
            Optional<Usuario> optionalUsuario = repository.findOptionalById(id);
            if (optionalUsuario.isPresent()) {
                return Response.status(Response.Status.OK).entity(optionalUsuario.get()).build();
            } else {
                Map<String,String> map = new HashMap<>(Map.of("tipo", "info"));
                map.put("mensagem ", "Usuario não encontrado");
              return Response.status(Response.Status.NOT_FOUND).entity(map).build();
            }
        }catch (Exception e){
            Map<String,String> map = new HashMap<>(Map.of("tipo", "erro"));
            map.put("message ", "Falha ao pesquisar usuário");
            logger.error(map.get("message"));
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(map).build();
        }
    }

    @POST
    @Path("/{login}/{senha}")
    public Response autenticar(@PathParam("login") String login,@PathParam("senha") String senha){
//        Response response;
//        String mensagem;
//        try {
//            Optional<Usuario> optional = repository.autenticar(login, senha);
//            if (optional.isPresent()) {
//                Sessao sessao = new Sessao(optional.get());
//                sessao.setAtiva(true);
//                response = Response.status(Response.Status.OK).entity(sessao).build();
//                mensagem = "Acesso autorizado : " + sessao.getUsuario().getLogin();
//                logger.info(mensagem);
//            } else {
//                mensagem = "Acesso Negado : " + login;
//                Map<String, String> map = new HashMap<>(Map.of("tipo", "info"));
//                map.put("mensagem",mensagem);
//                response = Response.status(Response.Status.NOT_FOUND).entity(map).build();
//                logger.warn(mensagem);
//            }
//        }catch (Exception e){
//            mensagem = "Falha durante autenticacao : " + login;
//            Map<String,String> map = new HashMap<>(Map.of("tipo", "erro"));
//            map.put("mensagem", mensagem );
//            response =  Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(map).build();
//            logger.error(mensagem);
//        }
//        return  response;
        return null;
    }
}