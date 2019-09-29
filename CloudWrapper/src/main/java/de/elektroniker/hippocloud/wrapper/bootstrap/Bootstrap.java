package de.elektroniker.hippocloud.wrapper.bootstrap;

import de.elektroniker.hippocloud.wrapper.CloudWrapper;

/******************************************************************
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 24.09.2019 / 12:16        
 *    Orginal Class: Bootstrap
 ******************************************************************/


public class Bootstrap {

    public static void main(String[] args) {
        CloudWrapper wrapperMaster = CloudWrapper.create();
        wrapperMaster.start();

    }


}
