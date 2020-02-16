package br.com.flavio.model;

import br.com.flavio.enumeradores.CategoriaUsuario;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Usuario {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    String login;
    String senha;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "categoria_usuario")
    CategoriaUsuario categoriaUsuario;
    @Column(name = "data_cadastro")
    Date dataCadastro;
    @Column(name = "data_atualizacao")
    Date dataAtualizacao;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id")
    Pessoa pessoa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public CategoriaUsuario getCategoriaUsuario() {
        return categoriaUsuario;
    }

    public void setCategoriaUsuario(CategoriaUsuario categoriaUsuario) {
        this.categoriaUsuario = categoriaUsuario;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
