package br.com.flavio.search;

import br.com.flavio.model.Usuario;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class UsuarioSearch {
    @Inject
    EntityManager em;

    public List<Usuario> listAll(){
        return em.createQuery("from Usuario",Usuario.class).getResultList();
    }
}
