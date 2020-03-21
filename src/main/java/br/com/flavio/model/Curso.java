package br.com.flavio.model;

import java.util.List;

public class Curso {
    Long id;
    String nome;
    String descricao;
    Long duracaoSemestres;
    List<Materia> materias;
    boolean deletado;
}
