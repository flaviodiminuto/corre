package br.com.flavio.model;


import javax.persistence.*;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Pessoa {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    String nome;
    @Column(name = "data_nascimento", nullable = false)
    Date dataNascimento;

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

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

}
