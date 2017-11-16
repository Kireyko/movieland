package com.kireyko.movieland.util;

import com.kireyko.movieland.entity.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class JsonManualConverter {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private static final String COMMA_SEPARATOR = ",";
    private static final String COLON_SEPARATOR = ":";
    private static final String LINE_SEPARATOR = System.lineSeparator();


    /*        StringBuilder sb = new StringBuilder();
        if(null !=movies && movies.size()>0){

            for (Movie movie: movies){
                Object[] movieFieldsShort = {
                        movie.getId(), movie.getMoviename(), movie.getNameNative(), movie.getYear(),  movie.getPrice(),movie.getRating(), movie.getPoster()
                };
                if (sb.length() !=0) {
                    sb.append(",");
                    sb.append(LINE_SEPARATOR);
                } else{
                    sb.append("[");
                    sb.append(LINE_SEPARATOR);
                }
                moviesAllJson = jsonConverter.toJson( movieFieldNamesShort, movieFieldsShort );
                sb.append(moviesAllJson);
            }
            sb.append(LINE_SEPARATOR);
            sb.append("]");
        }
        */

       public String toJson(String[] fieldNames, Object[] fieldObjects ) {
        StringBuilder json = new StringBuilder();

        json.append("{");
        json.append(LINE_SEPARATOR);
        for (int i = 0; i < fieldNames.length; i++) {

            json.append(surroundByQuotes(fieldNames[i]));
            json.append(COLON_SEPARATOR);

            if(fieldObjects[i].getClass().isArray()){
                json.append("[");
                json.append(LINE_SEPARATOR);

                String[] internalStringArray = (String[]) fieldObjects[i];
                StringBuilder internalArray = new StringBuilder();
                for (int n =0; n<internalStringArray.length; n++) {
                    if(internalArray.length()!=0){
                        internalArray.append(",");
                        internalArray.append(LINE_SEPARATOR);
                    }
                    internalArray.append("{");
                    internalArray.append(LINE_SEPARATOR);
                    internalArray.append(surroundByQuotes("id"));
                    internalArray.append(COLON_SEPARATOR);
                    internalArray.append(n+1);
                    internalArray.append(COMMA_SEPARATOR);
                    internalArray.append(LINE_SEPARATOR);

                    internalArray.append(surroundByQuotes("name"));
                    internalArray.append(COLON_SEPARATOR);
                    internalArray.append(surroundByQuotes(internalStringArray[n]));
                    internalArray.append(LINE_SEPARATOR);
                    internalArray.append("}");
                }

                json.append(internalArray);
                json.append(LINE_SEPARATOR);
                json.append("]");

            }
            else json.append(surroundByQuotes(fieldObjects[i]));


            if (i != fieldNames.length - 1) {
                json.append(COMMA_SEPARATOR);
                json.append(LINE_SEPARATOR);
            }

        }
        json.append(LINE_SEPARATOR);
        json.append("}");
        return json.toString();
    }

    private String surroundByQuotes(Object value) {
        return "\"" + value + "\"";
    }

}
