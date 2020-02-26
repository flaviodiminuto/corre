package br.com.flavio.Repository;

import br.com.flavio.model.Skill;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

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
public class SkillRepository implements PanacheRepository<Skill> {

    @Inject
    EntityManager em;

    public List<Skill> findByNameSuggestion(String nameSuggestion){
        //todo -- ajustar as buscas
        Map<String, Object> params = new HashMap<>();
        params.put("nameSuggestion", nameSuggestion);
        return list("nome like \'%:nameSuggestion%\' ", params);
    }

    public Skill findByName(String nome){
        return find("nome",nome).firstResult();
    }

//    @Transactional
//    public List<Skill> salvarListaHibernate(List<Skill> skillList){
//        skillList.forEach(em::persist);
//        return skillList;
//    }

    @Transactional
    public List<Skill> salvarListaPanache(List<Skill> skillList){
        try{
            skillList.forEach(this::persistAndFlush);
            return skillList;
//            skillList.forEach(skill -> {
//                persist(skill);
//                persistidos.add(skill);
//            });
        }catch (PersistenceException pe){
            //todo - - adicionar log
            pe.printStackTrace();
        }
        return new ArrayList<>();
    }
}
