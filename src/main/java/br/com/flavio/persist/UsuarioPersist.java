package br.com.flavio.persist;

import br.com.flavio.model.Usuario;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@ApplicationScoped
public class UsuarioPersist {

    @Inject
    EntityManager em;

    @Transactional
    public void persist(Usuario usuario){
        em.persist(usuario);
    }
}
