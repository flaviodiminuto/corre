package br.com.flavio.Repository.domain;

import br.com.flavio.Repository.domain.simpler.SkillSimplerRepository;
import br.com.flavio.model.Skill;
import br.com.flavio.model.simpler.SkillSimpler;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Parameters;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class SkillRepository implements PanacheRepositoryBase<Skill,Long> {

    @Inject
    EntityManager em;
    @Inject
    SkillSimplerRepository simpler;


    @Transactional
    public Skill create(Skill skill){
        persistAndFlush(skill);
        return skill;
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
