package br.com.flavio.business.controllers;

import br.com.flavio.model.Pessoa;
import br.com.flavio.persist.PessoaPersist;
import br.com.flavio.search.PessoaSearch;
import br.com.flavio.util.StringUtil;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Date;

@Path("/pessoa")
@Produces(MediaType.APPLICATION_JSON)
public class ControllerPessoa {
    @Inject
    PessoaPersist pessoaPersist;

    @POST
    @Path("/save/{nome}")
    public Pessoa savePessoa(@PathParam("nome") String nome) {
        if(StringUtil.notNullAndNotEmpty(nome)) {
            Pessoa pessoa = PessoaSearch.getPessoa(nome);
            pessoa.setDataNascimento(new Date());
            pessoaPersist.persist(pessoa);
            return pessoa;
        }
        return new Pessoa();
    }

}
