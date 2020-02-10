package br.com.flavio.persist;

import br.com.flavio.model.Aluno;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@ApplicationScoped
public class AlunoPersist {

    @Inject
    EntityManager em;

    @Transactional
    public void save(Aluno aluo){
        em.persist(aluo);
    }
}
