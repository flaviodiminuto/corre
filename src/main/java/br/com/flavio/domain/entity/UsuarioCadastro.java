package br.com.flavio.domain.entity;

public class UsuarioCadastro {
    private String login;
    private String senha;

    public UsuarioCadastro() { }

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
}
