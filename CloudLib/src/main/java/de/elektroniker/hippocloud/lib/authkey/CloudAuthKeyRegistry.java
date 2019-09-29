package de.elektroniker.hippocloud.lib.authkey;

import java.io.*;

/******************************************************************
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 24.09.2019 / 16:12        
 *    Orginal Class: CloudAuthKeyRegistry
 ******************************************************************/


public class CloudAuthKeyRegistry implements AuthKeyRegistry {
    private AuthKey authKey;
    private final File authKeyFile = new File("./auth.key");

    public static CloudAuthKeyRegistry create(){
        return new CloudAuthKeyRegistry();
    }

    @Override
    public AuthKey getKey() {
        if(authKey==null){
            authKey = () -> {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(authKeyFile));
                String keyString = bufferedReader.readLine();
                return keyString;
            };
        }
        return authKey;
    }
}
