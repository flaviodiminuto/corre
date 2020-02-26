package br.com.flavio.model;


import javax.persistence.*;

@Entity
public class Aluno extends Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long RA;
    boolean deletado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRA() {
        return RA;
    }

    public void setRA(Long RA) {
        this.RA = RA;
    }

    public boolean isDeletado() {
        return deletado;
    }

    public void setDeletado(boolean deletado) {
        this.deletado = deletado;
    }
}
