package de.elektroniker.hippocloud.lib.config;

import java.io.File;
import java.io.IOException;
import java.util.List;

/******************************************************************
 *    Urheberrechtshinweis                                                       
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 22.09.2019 / 18:05        
 *    Orginal Class: Config
 *
 *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.                    
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,       
 *    bei Thomas Michaelis. Alle Rechte vorbehalten.                      
 *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,        
 *    öffentlichen Zugänglichmachung oder andere Nutzung           
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Thomas Michaelis.  
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
