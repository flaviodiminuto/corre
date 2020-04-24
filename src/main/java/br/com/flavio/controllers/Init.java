package br.com.flavio.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

/**
 * @author flaviodiminuto
 * @version 1.0
 *
 * Apresenta mensagem indicando em qual modo a aplicacao iniciou
 */
@ApplicationScoped
public class Init {

    private Log logger = LogFactory.getLog(this.getClass());
    @ConfigProperty(name = "profile.name", defaultValue = "NAO RECONHECIDO - FALHA")
    String profile;

    void onStart(@Observes StartupEvent env){
        logger.info(String.format("%n%n%n- - - - - INICIADO EM MODO %s - - - - -%n%n%n",profile));
    }
}
