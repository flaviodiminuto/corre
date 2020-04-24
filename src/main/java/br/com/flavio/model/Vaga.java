package br.com.flavio.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Set;

public class Vaga {
    @Id
    Long id;
    @Column(nullable = false)
    String descricao;
    @Column(nullable = false)
    Usuario divulgador;
    @Column(nullable = false)
    AreaAtuacao area;

    Set<Skill> requisitos;
    Set<Skill> desejaveis;
    boolean deletado;
}
