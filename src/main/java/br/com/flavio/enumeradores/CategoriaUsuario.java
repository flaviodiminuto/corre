package br.com.flavio.enumeradores;

public enum CategoriaUsuario {
    USUARIO(1), ADMINISTRADOR(2), VISITANTE(3);

    private int categoria;

    CategoriaUsuario(int categoria){
        this.categoria = categoria;
    }

    public int getCategoria() {
        return categoria;
    }
}
