package br.com.flavio.util;

import java.util.HashMap;
import java.util.Map;

public class ControllerResponseUtil {
    public static String getMessageErrorJSON(String mensagem){
        return getMessageJson("erro",mensagem);
    }

    public static String getMessageInfoTextJSON(String mensagem){
        return getMessageJson("info",mensagem);
    }

    private static String getMessageJson(String key, String value){
        return String.format("{\"tipo\": \"%s\",\n\"mensagem\": \"%s\"}",key, value);
    }
}
