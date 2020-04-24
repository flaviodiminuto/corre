package br.com.flavio.model;

import br.com.flavio.enumeradores.CategoriaUsuario;
import br.com.flavio.model.Pessoa;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.*;
import java.util.Date;

@Entity
public class Usuario {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String login;
    private String senha;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "categoria_usuario")
    private CategoriaUsuario categoriaUsuario;
    @Column(name = "data_cadastro")
    @JsonbDateFormat("yyyy-MM-dd HH:MM:ss")
    private Date dataCadastro;
    @Column(name = "data_atualizacao")
    @JsonbDateFormat("yyyy-MM-dd HH:MM:ss")
    private Date dataAtualizacao;
    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private Pessoa pessoa;
    private boolean deletado;

    public Usuario(){}

    public Usuario(Long id, String login, String senha, CategoriaUsuario categoriaUsuario, Date dataCadastro, Date dataAtualizacao, Pessoa pessoa, boolean deletado) {
        this.id = id;
        this.login = login;
        this.senha = senha;
        this.categoriaUsuario = categoriaUsuario;
        this.dataCadastro = dataCadastro;
        this.dataAtualizacao = dataAtualizacao;
        this.pessoa = pessoa;
        this.deletado = deletado;
    }

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

    public boolean isDeletado() {
        return deletado;
    }

    public void setDeletado(boolean deletado) {
        this.deletado = deletado;
    }
}
