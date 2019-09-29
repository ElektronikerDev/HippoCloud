package de.elektroniker.hippocloud.lib.setup;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.UUID;
import java.util.function.Consumer;

/******************************************************************
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 26.09.2019 / 21:55        
 *    Orginal Class: Step
 ******************************************************************/

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Step {

    String message();
    String[] invalidInputs();
}
