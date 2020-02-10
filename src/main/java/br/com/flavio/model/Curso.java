package br.com.flavio.model;

import java.util.List;

public class Curso {
    Long id;
    String nome;
    String descricao;
    Long duracaoSemestres;
    List<Materia> materias;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getDuracaoSemestres() {
        return duracaoSemestres;
    }

    public void setDuracaoSemestres(Long duracaoSemestres) {
        this.duracaoSemestres = duracaoSemestres;
    }

    public List<Materia> getMaterias() {
        return materias;
    }

    public void setMaterias(List<Materia> materias) {
        this.materias = materias;
    }
}
