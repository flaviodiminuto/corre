package br.com.flavio.util;

import java.util.List;

public class ListUtil {

    public static boolean notNullAndEmpty(List lista){
        return lista != null && lista.size() > 0;
    }
}
