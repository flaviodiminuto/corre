package br.com.flavio.util;

public class ControllerResponseUtil {
    public static String getMessageErrorJSON(String mensagem){
        return getJSON("mensage-de-erro",mensagem);
    }

    public static String getMessageResponseTextJSON(String mensagem){
        return getJSON("resonse-text",mensagem);
    }

    private static String getJSON(String key, String value){
        return String.format("{\"%s\": \"%s\"}",key, value);
    }
}
