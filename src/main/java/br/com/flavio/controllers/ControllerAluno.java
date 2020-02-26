package br.com.flavio.controllers;

import br.com.flavio.model.Aluno;
import br.com.flavio.model.Usuario;
import br.com.flavio.dao.AlunoPersist;
import br.com.flavio.util.StringUtil;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Path("/aluno")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ControllerAluno {

    @Inject
    AlunoPersist alunoPersist;

    private static AtomicLong alunoId = new AtomicLong();

    @GET
    @Path("/list")
    public List<Aluno> list(Usuario usuario){
        List<Aluno> alunoList = new ArrayList<>();

        return alunoList;
    }

    @GET
    @Path("/example")
    public Response example(){
        return Response.ok(new Aluno()).build();
    }

    @PUT
    @Path("/atualiza")
    public Response atualiza(Aluno aluno){
        alunoPersist.update(aluno);
        return Response
                .ok(aluno)
                .build();
    }
}
