package de.elektroniker.hippocloud.lib.console.setup.wrapper;

import de.elektroniker.hippocloud.lib.setup.Step;

/******************************************************************
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 27.09.2019 / 10:27        
 *    Orginal Class: WrapperSetupSteps
 ******************************************************************/


public class WrapperSetupSteps {

    @Step(message = "Whats the Name of this Wrapper?", invalidInputs = {"127.0.0.1", "localhost"})
    private String name;

    @Step(message = "Whats the Hostname/Hostadress of this Wrapper?", invalidInputs = {"127.0.0.1", "localhost"})
    private String hostname;

    @Step(message = "Whats the Port of this Wrapper?", invalidInputs = {"80", "25565", "443"})
    private Integer port;

    @Step(message = "How much ram (in MB) does the wrapper get?", invalidInputs = {"-1", "0", "1"})
    private Integer ram;


}
