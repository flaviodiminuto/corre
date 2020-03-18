package br.com.flavio.Repository.domain;

import br.com.flavio.model.usuario.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Parameters;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepositoryBase<Usuario,Long> {

    @Inject
    EntityManager em;

    //update
    @Transactional
    public void update(Usuario usuario){
        if(findById(usuario.getId())!=null)
            em.merge(usuario);
    }

    //delete lógico
    @Transactional
    public int delete(Long id) {
        return update("deletado = true, dataAtualizacao = :data where id = :id", Parameters.with("data",new Date()).and("id",id).map());
    }

    public Optional<Usuario> autenticar(String login, String senha){
        Usuario usuario = find("login=:login and senha=:senha ",Parameters.with("login", login).and("senha",senha).map()).firstResult();
        return Optional.of(usuario);
    }
}
