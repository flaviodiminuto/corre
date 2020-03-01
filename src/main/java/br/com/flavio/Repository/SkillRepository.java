package br.com.flavio.Repository;

import br.com.flavio.model.Skill;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Parameters;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class SkillRepository implements PanacheRepositoryBase<Skill,Long> {

    @Inject
    EntityManager em;

    private static final Integer limite_por_busca = 50;

    @Transactional
    public Skill create(Skill skill){
        persistAndFlush(skill);
        return skill;
    }

    public List<Skill> listByNameSuggestion(String suggestion){
     //PahacheQuery para poder limitar a quantidade retornada na lista
         PanacheQuery<Skill> query = find("from Skill vo where vo.nome like :nome and vo.deletado = :deletado",
                Parameters.with("nome",String.format("%%%s%%",suggestion))
                        .and("deletado",false)
                        .map()
        ).page(Page.ofSize(limite_por_busca));
         return query.list();
    }

    public Skill findByName(String suggestion){
        return find("from Skill vo where vo.descricao like :nome and vo.deletado = :deletado",Parameters.with("nome",String.format("%%%s%%",suggestion)).and("deletado",false).map()).firstResult();
    }

    @Transactional
    public List<Skill> salvarListaPanache(List<Skill> skillList){
        try{
            persist(skillList);
            return skillList;
        }catch (PersistenceException pe){
            //todo - - adicionar log
            pe.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Transactional
    public int deletarSkill(Long id){
        return update("deletado = true where id = :id",Parameters.with("id",id).map());
    }

    @Transactional
    public Skill update(Skill skill){
        return em.merge(skill);
    }
}
