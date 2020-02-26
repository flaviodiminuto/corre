package desenvolvimento.tool;

import br.com.flavio.Repository.SkillRepository;
import br.com.flavio.model.Skill;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class InitSkillsTable  {
    @Inject
    SkillRepository skillRepository;

    public List<Skill> initTable(){
        List<Skill> skillList = new ArrayList<>();
        //Long id, String nome, String descricao, boolean deletado
        skillList.add(new Skill(null,"Java", "Linguagem de programação", false));
        skillList.add(new Skill(null,"C#", "Linguagem de programação", false));
        skillList.add(new Skill(null,"JavaScript", "Linguagem de programação", false));
        skillList.add(new Skill(null,"Ruby", "Linguagem de programação", false));
        skillList.add(new Skill(null,"Docker", "Ferramenta de container de aplicação", false));
        skillList.add(new Skill(null,"Kubernetes", "Orquestrador de containeres de aplicação", false));

        return skillRepository.salvarListaPanache(skillList);
        //skillRepository.salvarListaHibernate(skillList);
    }

}
