package br.com.flavio.model.usuario;

import br.com.flavio.enumeradores.CategoriaUsuario;

public class UsuarioAlteracao {
    private Long id;
    private String senha;
    private CategoriaUsuario categoriaUsuario = CategoriaUsuario.USUARIO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
