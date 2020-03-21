package br.com.flavio.util;

import java.util.HashMap;
import java.util.Map;

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

    public static Map<String,String> mapResponse(String tipo, String mensagem){
        Map<String, String> map = new HashMap<>();
        map.put("tipo", tipo);
        map.put("mensagem",mensagem);
        return map;
    }
}
