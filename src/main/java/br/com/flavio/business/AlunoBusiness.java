package br.com.flavio.business;

import br.com.flavio.model.Aluno;


public class AlunoBusiness {

    public static Aluno getAluno(String nome, Long RA){
        Aluno aluno = new Aluno();
        aluno.setNome(nome);
        aluno.setRA(RA);
        return aluno;
    }
}
