package br.com.flavio.model;


import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Aluno extends Pessoa {

    Long RA;

    @Column(nullable = false)
    public Long getRA() {
        return RA;
    }

    public void setRA(Long RA) {
        this.RA = RA;
    }

}
