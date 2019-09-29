package de.elektroniker.hippocloud.lib.config;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;

/******************************************************************
 *    Urheberrechtshinweis                                                       
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 24.09.2019 / 16:07        
 *    Orginal Class: SimpleFileWriter
******************************************************************/


public interface SimpleFileWriter {
    default void writeToFile(File file, String text, boolean append) throws Exception {
        if (!file.exists()) file.createNewFile();

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, append));
        if (append) {
            bufferedWriter.append(text);
        } else {
            bufferedWriter.write(text);
        }
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    default void writeToFile(File file, Config config, boolean append) throws Exception {
        HashMap<String, Object> configMap = new HashMap<>();
        config.getKeys().forEach(key -> configMap.put(key, config.get(key)));

        Gson gson = new Gson();
        writeToFile(file, gson.toJson(configMap), append);

    }


}
