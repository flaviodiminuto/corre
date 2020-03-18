package br.com.flavio.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

public class Curso {
    Long id;
    String nome;
    String descricao;
    Long duracaoSemestres;
    List<Materia> materias;
    boolean deletado;
}
