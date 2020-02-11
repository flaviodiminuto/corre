package br.com.flavio.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CategoriaUsuario {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    String nome;

    public CategoriaUsuario() {
    }

    public CategoriaUsuario(String nome) {
        this.nome = nome;
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
}
