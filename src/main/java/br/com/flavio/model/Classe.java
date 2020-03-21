package br.com.flavio.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
public class Classe {
    Long id;
    Curso curso;
    List<Aluno> alunos;
    Turno turno;
    boolean deletado;

}
