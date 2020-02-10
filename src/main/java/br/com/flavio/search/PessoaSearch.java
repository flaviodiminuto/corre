package br.com.flavio.search;

import br.com.flavio.model.Pessoa;

public class PessoaSearch {

    public static Pessoa getPessoa(String nome){
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(nome);
        return pessoa;
    }
}
