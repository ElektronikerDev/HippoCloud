package de.elektroniker.hippocloud.lib.authkey;

import de.elektroniker.hippocloud.lib.config.SimpleFileWriter;
import de.elektroniker.hippocloud.lib.utils.Utils;
import org.apache.commons.lang.RandomStringUtils;
import java.io.File;

/******************************************************************
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 24.09.2019 / 16:02        
 *    Orginal Class: CloudAuthKeyFactory
 ******************************************************************/


public class CloudAuthKeyFactory implements AuthKeyFactory, Utils, SimpleFileWriter {

    public static CloudAuthKeyFactory create() {
        return new CloudAuthKeyFactory();
    }

    @Override
    public AuthKey createAuthKey() {
        File authKeyFile = new File("./auth.key");
        if (authKeyFile.exists()) {
            log("AuthKey File already exist!");
            return null;
        }else{
            log("Create new Authkey...");
        }
        AuthKey authKey = () -> RandomStringUtils.randomAlphanumeric(120);
        try {
            writeToFile(authKeyFile, authKey.getKeyString(), false);
            log("Created new AuthKey!");
        } catch (Exception e) {
            log("Failed creating AuthKey File!");
        }
        return authKey;
    }
}