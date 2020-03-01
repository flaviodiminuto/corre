package br.com.flavio.model;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Pessoa {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String nome;
    @Column(name = "data_nascimento")
    private LocalDateTime dataNascimento;
    private boolean deletado;

    public Pessoa() {  }
    public Pessoa(String nome) {
        this.nome = nome;
    }
    public Pessoa(String nome, LocalDateTime dataNascimento) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

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

    public LocalDateTime getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDateTime dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public boolean isDeletado() {
        return deletado;
    }

    public void setDeletado(boolean deletado) {
        this.deletado = deletado;
    }
}
