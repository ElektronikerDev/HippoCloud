package de.elektroniker.hippocloud.lib.console.setup.proxygroup;

import de.elektroniker.hippocloud.lib.setup.Step;

/******************************************************************
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 27.09.2019 / 10:27        
 *    Orginal Class: WrapperSetupSteps
 ******************************************************************/


public class ProxyGroupSetupSteps {

    @Step(message = "Whats the Name of the ProxyGroup?", invalidInputs = {"default"})
    private String hostname;

    @Step(message = "Whats the Name of the Wrapper?", invalidInputs = {})
    private String wrapper;

    @Step(message = "Min. Memory?", invalidInputs = {"-1","0","1"})
    private Integer minMemory;

    @Step(message = "Max. Memory?", invalidInputs = {})
    private Integer maxMemory;

    @Step(message = "Is Static?", invalidInputs = {})
    private Boolean isStatic;

    @Step(message = "Min. Server Online?", invalidInputs = {})
    private Integer minServerOnline;

    @Step(message = "Max. Server Online?", invalidInputs = {})
    private Integer maxServerOnline;





}
