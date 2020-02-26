package br.com.flavio.dao;

import br.com.flavio.model.Aluno;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;

@ApplicationScoped
public class AlunoPersist {

    @Inject EntityManager em;

    @Transactional
    public Aluno save(Aluno aluno){
        try{
            em.persist(aluno);
        }catch (Exception e){
        }
        return aluno;
    }

    //todo  - - testar o OK e no NotOK este metodo.
    @Transactional
    public Response update(Aluno aluno){
        try {
            em.merge(aluno);
            return Response.ok(aluno).tag("Aluno Atualizado").build();
        }catch (Exception e){
            return Response.notModified("Nao foi possivel atualizar Aluno").build();
        }
    }
}
