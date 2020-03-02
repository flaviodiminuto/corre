package br.com.flavio.Repository.domain;

import br.com.flavio.model.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Parameters;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Date;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepositoryBase<Usuario,Long> {

    @Inject
    EntityManager em;

    @Override
    public void persist(Usuario usuario) {
        usuario.setDataCadastro(new Date());
        usuario.setDataAtualizacao(null);
        usuario.setDeletado(false);
        persistAndFlush(usuario);
    }

    //update
    @Transactional
    public void update(Usuario usuario){
        em.merge(usuario);
    }

    //delete lógico
    @Transactional
    public int delete(Long id) {
        return update("deletado = true, dataAtualizacao = :data where id = :id", Parameters.with("data",new Date()).and("id",id).map());
    }
}
