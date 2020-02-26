package br.com.flavio.controllers;

import br.com.flavio.enumeradores.CategoriaUsuario;
import br.com.flavio.model.Pessoa;
import br.com.flavio.model.Usuario;
import br.com.flavio.dao.UsuarioPersist;
import br.com.flavio.search.UsuarioSearch;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/usuario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ControllerUsuario {

    @Inject UsuarioPersist service;
    @Inject UsuarioSearch busca;

    @POST
    @Path("/create")
    public Usuario createUsuario(){
        Usuario usuario = new Usuario();
        usuario.setLogin("teste");
        usuario.setSenha("senha");
        usuario.setPessoa(new Pessoa("Flávio"));
        usuario.setCategoriaUsuario(CategoriaUsuario.VISITANTE);

        service.persist(usuario);
        return usuario;
    }

    @GET
    @Path("/list")
    public List<Usuario> list(){
        return busca.listAll();
    }
}
