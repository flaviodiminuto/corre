package br.com.flavio.business;

import br.com.flavio.model.CategoriaUsuario;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class IniciaCategoriasUsuario {
    @Inject
    EntityManager em;


    public List<CategoriaUsuario> listaCategorias(){
        List<CategoriaUsuario> categoriasList = new ArrayList<>();
        categoriasList.add(new CategoriaUsuario("Aluno"));
        categoriasList.add(new CategoriaUsuario("Professor"));
        categoriasList.add(new CategoriaUsuario("Administrador"));
        return categoriasList;
    }

    public List<CategoriaUsuario> list(){
        String hql = "from CategoriaUsuario";
        try{
            return em.createQuery(hql,CategoriaUsuario.class)
                    .getResultList();
        }catch (Exception e){
            //Todo - adicionar Log
            return new ArrayList<>();
        }
    }

    @Transactional
    public void persist(CategoriaUsuario categoria){
        if(categoria != null) {
            em.persist(categoria);
            em.flush();
            em.clear();
        }
    }

}
