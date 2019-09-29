package de.elektroniker.hippocloud.lib.file;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/******************************************************************
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 27.09.2019 / 14:50        
 *    Orginal Class: DataFactory
 ******************************************************************/


public interface DataFactory {

    default boolean create(String path, CloudDataType dataType) {

        if (dataType.equals(CloudDataType.FILE)) {
            //FILE
            String[] pathArgs = path.split("/");
            File file = new File(pathArgs[pathArgs.length - 1]);
            StringBuilder pathFolder = new StringBuilder();
            Arrays.stream(Arrays.copyOf(pathArgs, pathArgs.length - 1)).forEach(s -> pathFolder.append(s + "/"));
            File folderPath = new File(pathFolder.toString());
            folderPath.mkdirs();
            try {
                file.createNewFile();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }

        } else {
            //FOLDER
            File file = new File(path);
            try {
                file.mkdirs();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
    }

}
