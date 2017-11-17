package com.kireyko.movieland.dao.jdbc.sqlbuilder;

import java.util.Map;

public class SqlBuilder {

    public static String enrichQuery(String sqlStatement,Map<String, String> parameters) {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String,String> entry : parameters.entrySet()) {
            if (entry.getValue() != null) {
                if (sb.length() != 0) {
                    sb.append(", ");
                }else{
                    sb.append(" ORDER BY ");
                }
                //add some logic for checking values of parameters and link them to fields in database
                sb.append(entry.getKey());
                sb.append(" ");
                sb.append(entry.getValue());
            }
        }
        return sqlStatement + sb.toString();
    }
}
