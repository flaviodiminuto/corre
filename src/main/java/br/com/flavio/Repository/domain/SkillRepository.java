package br.com.flavio.Repository.domain;

import br.com.flavio.Repository.domain.simpler.SkillSimplerRepository;
import br.com.flavio.model.Skill;
import br.com.flavio.model.simpler.SkillSimpler;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Parameters;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class SkillRepository implements PanacheRepositoryBase<Skill,Long> {
    @Inject
    EntityManager em;
    private Log log = LogFactory.getLog(SkillSimpler.class);

    @Transactional
    public Skill create(Skill skill){
        skill.setId(null);
        skill.setDataCriacao(new Date());
        skill.setDataAtualizacao(null);
        skill.setDeletado(false);
        persistAndFlush(skill);
        return skill;
    }

    @Transactional
    public List<Skill> salvarListaPanache(List<Skill> skillList){
        try{
            persist(skillList);
            return skillList;
        }catch (PersistenceException pe){
            log.fatal(pe);
        }
        return new ArrayList<>();
    }

    @Transactional
    public int deletarSkill(Long id){
        return update("deletado = true, dataAtualizacao = :data where id = :id",Parameters.with("data",new Date()).and("id",id).map());
    }

    @Transactional
    public Skill update(Skill skill){
        skill.setDataAtualizacao(new Date());
       return em.merge(skill);
    }
}
