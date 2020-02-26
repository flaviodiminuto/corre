package br.com.flavio.dao;

import br.com.flavio.model.Pessoa;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@ApplicationScoped
public class PessoaPersist {

    @Inject
    EntityManager em;

    @Transactional
    public  void persist(Pessoa pessoa){
        em.persist(pessoa);
    }
}
