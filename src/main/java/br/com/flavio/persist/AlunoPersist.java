package br.com.flavio.persist;

import br.com.flavio.model.Aluno;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class AlunoPersist {

    @Inject EntityManager em;
    @Inject Logger logger;

    @Transactional
    public Aluno save(Aluno aluno){
        try{
            em.persist(aluno);
        }catch (Exception e){
            logger.log(Level.ALL,"Falha ao salvar Aluno :"+aluno.getNome(),e);
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
