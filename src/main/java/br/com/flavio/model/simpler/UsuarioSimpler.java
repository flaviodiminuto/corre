package br.com.flavio.model.simpler;

import br.com.flavio.enumeradores.CategoriaUsuario;

import javax.persistence.*;

/**
 * Classe para representar usuários simplificados
 * sem expor senha e dados que não sejam relevantes
 * em listas de usuários
 */
@Entity
@Table(name = "usuario")
public class UsuarioSimpler {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String login;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "categoria_usuario")
    private CategoriaUsuario categoriaUsuario;
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

    public CategoriaUsuario getCategoriaUsuario() {
        return categoriaUsuario;
    }

    public void setCategoriaUsuario(CategoriaUsuario categoriaUsuario) {
        this.categoriaUsuario = categoriaUsuario;
    }

    public boolean isDeletado() {
        return deletado;
    }

    public void setDeletado(boolean deletado) {
        this.deletado = deletado;
    }
}
