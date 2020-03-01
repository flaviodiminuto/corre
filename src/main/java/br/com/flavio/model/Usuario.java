package br.com.flavio.model;

import br.com.flavio.enumeradores.CategoriaUsuario;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.*;
import java.time.LocalDateTime;

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
    private LocalDateTime dataCadastro;
    @Column(name = "data_atualizacao")
    @JsonbDateFormat("yyyy-MM-dd HH:MM:ss")
    private LocalDateTime dataAtualizacao;
    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private Pessoa pessoa;
    private boolean deletado;

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

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
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
