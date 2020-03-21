package br.com.flavio.repository;

import br.com.flavio.model.usuario.Usuario;
import br.com.flavio.model.usuario.UsuarioAlteracao;
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

    public Optional<Usuario> findOptionalById(Long id) {
        Usuario usuario = findById(id);
        return Optional.ofNullable(usuario);
    }

    //update

    /**
     *
     * @param usuarioAlteracao
     * @return
     * 0 - usuario inalterado <br/>
     * 1 - usuario atualizado <br/>
     * 2 - usuario inexistente <br/>
     * 3 - falha ao atualizar <br/>
     */
    @SuppressWarnings("JavaDoc")
    @Transactional
    public int update(UsuarioAlteracao usuarioAlteracao){
        try {
            Usuario usuario = findById(usuarioAlteracao.getId());
            if (usuario != null) {
                if(usuarioAlteracao.getSenha().equals(usuario.getSenha()))
                    return 0;
                else {
                    usuario.setSenha(usuarioAlteracao.getSenha());
                    usuario.setCategoriaUsuario(usuarioAlteracao.getCategoriaUsuario());
                    usuario.setDataAtualizacao(new Date());
                    em.merge(usuario);
                    return 1;
                }
            }
            return 2;
        }catch (Exception e){
            e.printStackTrace();
            return 3;
        }
    }

    //delete lógico
    @Transactional
    public int delete(Long id) {
        return update("deletado = true, dataAtualizacao = :data where id = :id", Parameters.with("data",new Date()).and("id",id).map());
    }

    public Optional<Usuario> autenticar(String login, String senha){
        Usuario usuario = find("login=:login and senha=:senha ",Parameters.with("login", login).and("senha",senha).map()).firstResult();
        return Optional.ofNullable(usuario);
    }
}
