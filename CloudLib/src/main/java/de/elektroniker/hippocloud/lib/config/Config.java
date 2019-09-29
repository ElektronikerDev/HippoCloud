package de.elektroniker.hippocloud.lib.config;

import java.io.File;
import java.io.IOException;
import java.util.List;

/******************************************************************
 *    Urheberrechtshinweis                                                       
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 22.09.2019 / 18:05        
 *    Orginal Class: Config
******************************************************************/


public interface Config {

    void read(File file);

    void set(String key, Object value);

    Object get(String key);

    void delete(String key);

    void save(File file) throws IOException;

    void unload();

    List<String> getKeys();

}
