package br.com.flavio.model;

import br.com.flavio.model.usuario.Usuario;

import javax.json.bind.annotation.JsonbTransient;
import java.util.Date;
import java.util.Optional;

public class Sessao {
    Date inicio = new Date();
    @JsonbTransient
    Date ultimaAtivadade = new Date();
    Usuario usuario;
    boolean ativa=false;

    public Sessao() {
    }

    public Sessao(Optional<Usuario> optionalUsuario){
        if(optionalUsuario.isPresent()) {
            ativa = optionalUsuario.isPresent();
            usuario = optionalUsuario.get();
        }
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getUltimaAtivadade() {
        return ultimaAtivadade;
    }

    public void setUltimaAtivadade(Date ultimaAtivadade) {
        this.ultimaAtivadade = ultimaAtivadade;
    }

    public Usuario getUsuario() {
        this.ultimaAtivadade = new Date();
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}