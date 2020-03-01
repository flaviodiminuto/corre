package br.com.flavio.Repository.domain.simpler;

import br.com.flavio.model.simpler.SkillSimpler;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Parameters;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class SkillSimplerRepository implements PanacheRepositoryBase<SkillSimpler,Long> {

    private static final Integer limite_por_busca = 50;
    public List<SkillSimpler> listByNameSuggestion(String suggestion){
        //PahacheQuery para poder limitar a quantidade retornada na lista
         PanacheQuery<SkillSimpler> query = find("from SkillSimpler vo where vo.nome like :nome and vo.deletado = :deletado",
                Parameters.with("nome",String.format("%%%s%%",suggestion))
                        .and("deletado",false)
                        .map()
        ).page(Page.ofSize(limite_por_busca));
        return query.list();
    }
}
