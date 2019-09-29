package de.elektroniker.hippocloud.lib.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/******************************************************************
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 22.09.2019 / 18:09        
 *    Orginal Class: CloudConfig
 ******************************************************************/


public class CloudConfig implements Config , SimpleFileWriter{
    private HashMap<String, Object> configData = new HashMap<>();
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static CloudConfig create(){
        return new CloudConfig();
    }

    @Override
    public void read(File file) {
        try {
            String content = FileUtils.readFileToString(file, Charset.defaultCharset());
            configData = gson.fromJson(content, HashMap.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void set(String key, Object value) {
        configData.put(key, value);
    }

    @Override
    public Object get(String key) {
        return configData.get(key);
    }


    @Override
    public void delete(String key) {
        configData.remove(key);
    }

    @Override
    public void save(File file) {
        String content = gson.toJson(configData);
        try {
            writeToFile(file, content, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void unload() {
        configData.clear();
    }

    @Override
    public List<String> getKeys() {
        return new ArrayList<>(configData.keySet());
    }


}
