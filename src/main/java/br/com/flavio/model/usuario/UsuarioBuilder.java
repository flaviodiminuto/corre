package br.com.flavio.model.usuario;

import br.com.flavio.enumeradores.CategoriaUsuario;
import br.com.flavio.model.Pessoa;

import java.util.Date;

public class UsuarioBuilder {
    Long id;
    String login;
    String senha;
    CategoriaUsuario categoriaUsuario;
    Date dataCadastro;
    Date dataAtualizacao;
    Pessoa pessoa;
    boolean deletado;

    public UsuarioBuilder(){}
    public UsuarioBuilder(String login, String senha){
        this.login = login;
        this.senha = senha;
    }
    public UsuarioBuilder id(Long id){
        this.id = id;
        return this;
    }
    public UsuarioBuilder login(String login){
        this.login = login;
        return this;
    }
    public UsuarioBuilder senha(String senha){
        this.senha = senha;
        return this;
    }
    public UsuarioBuilder categoriaUsuario(CategoriaUsuario categoriaUsuario){
        this.categoriaUsuario = categoriaUsuario;
        return this;
    }
    public UsuarioBuilder dataCadastro(Date dataCadastro){
        this.dataCadastro = dataCadastro;
        return this;
    }
    public UsuarioBuilder dataAtualizacao(Date dataAtualizacao){
        this.dataAtualizacao = dataAtualizacao;
        return this;
    }
    public UsuarioBuilder pessoa(Pessoa pessoa){
        this.pessoa = pessoa;
        return this;
    }
    public UsuarioBuilder deletado(boolean deletado ){
        this.deletado = deletado;
        return this;
    }
    public Usuario build(){
        return new Usuario(id,login,senha,categoriaUsuario,dataCadastro,dataAtualizacao,pessoa,deletado);
    }
}
