package br.com.flavio.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

public class Usuario {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    String login;
    String senha;
    @Column(name = "categoria_pessoa")
    CategoriaUsuario categoriaUsuario;
    @Column(name = "data_cadastro")
    Date dataCadastro;
    @Column(name = "data_atualizacao")
    Date dataAtualizacao;
}
