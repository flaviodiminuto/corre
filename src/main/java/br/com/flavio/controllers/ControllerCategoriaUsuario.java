package br.com.flavio.controllers;

import br.com.flavio.business.IniciaCategoriasUsuario;
import br.com.flavio.model.CategoriaUsuario;
import br.com.flavio.util.ListUtil;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Objects;

@Path("/init")
@Produces(MediaType.APPLICATION_JSON)
public class ControllerCategoriaUsuario {

    @Inject IniciaCategoriasUsuario iniciaCategoriasUsuario;

    @GET
    public String getCategorias(){
        inicia();
        return "iniciado";
    }
    public void inicia(){
        List<CategoriaUsuario> categoriasList = list();
        if(!ListUtil.notNullAndEmpty(categoriasList)){
            listaCategorias().stream().filter(Objects::nonNull).forEach(this::persist);
        }
    }

    private List<CategoriaUsuario> list() {
        return iniciaCategoriasUsuario.list();
    }

    private void persist(CategoriaUsuario categoriaUsuario) {
        iniciaCategoriasUsuario.persist(categoriaUsuario);
    }

    private List<CategoriaUsuario> listaCategorias() {
        return iniciaCategoriasUsuario.listaCategorias();
    }


}
