package br.com.flavio.Repository.domain.simpler;

import br.com.flavio.model.Materia;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MateriaRepository implements PanacheRepositoryBase<Materia, Long> {
}
