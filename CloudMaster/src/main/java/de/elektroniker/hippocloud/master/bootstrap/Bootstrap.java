package de.elektroniker.hippocloud.master.bootstrap;

import de.elektroniker.hippocloud.master.CloudMaster;

/******************************************************************
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 22.09.2019 / 12:08        
 *    Orginal Class: Bootstrap
 ******************************************************************/


public class Bootstrap {


    public static void main(String[] args) {
        CloudMaster cloudMaster = CloudMaster.create();
        cloudMaster.start();

    }


}
