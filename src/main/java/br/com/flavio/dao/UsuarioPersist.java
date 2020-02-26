package br.com.flavio.dao;

import br.com.flavio.model.Usuario;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Date;

@ApplicationScoped
public class UsuarioPersist {

    @Inject
    EntityManager em;

    @Transactional
    public void persist(Usuario usuario){
        usuario.setDataCadastro(new Date());
        em.persist(usuario);
    }
}
