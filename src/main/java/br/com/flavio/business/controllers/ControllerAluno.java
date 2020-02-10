package br.com.flavio.business.controllers;

import br.com.flavio.business.AlunoBusiness;
import br.com.flavio.model.Aluno;
import br.com.flavio.persist.AlunoPersist;
import br.com.flavio.util.StringUtil;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

public class ControllerAluno {

    @Inject
    AlunoPersist alunoPersist;

    private static AtomicLong alunoId = new AtomicLong();

    @POST
    @Path("/save/{nome}")
    public Response saveAluno(@PathParam("nome") String nome){
        if(StringUtil.notNullAndNotEmpty(nome)){
            Aluno aluno = AlunoBusiness.getAluno(nome, alunoId.incrementAndGet() );
            aluno.setDataNascimento(new Date());
            alunoPersist.save(aluno);
            return Response.ok(aluno).build();
        }
        return Response.noContent().build();
    }
}
