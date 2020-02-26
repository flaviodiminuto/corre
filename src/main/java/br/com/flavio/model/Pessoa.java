package br.com.flavio.model;


import javax.persistence.*;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Pessoa {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    String nome;
    @Column(name = "data_nascimento")
    Date dataNascimento;
    boolean deletado;

    public Pessoa() {  }
    public Pessoa(String nome) {
        this.nome = nome;
    }
    public Pessoa(String nome, Date dataNascimento) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }
}
