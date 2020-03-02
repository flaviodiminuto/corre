package br.com.flavio.Repository.domain.simpler;

import br.com.flavio.model.simpler.UsuarioSimpler;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsuarioSimplerRepository implements PanacheRepositoryBase<UsuarioSimpler,Long> {

}
