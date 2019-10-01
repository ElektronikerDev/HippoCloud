package de.elektroniker.hippocloud.lib.console.setup.wrapper;

import com.google.gson.Gson;
import de.elektroniker.hippocloud.lib.CloudLib;
import de.elektroniker.hippocloud.lib.config.SimpleFileWriter;
import de.elektroniker.hippocloud.lib.console.Console;
import de.elektroniker.hippocloud.lib.console.DefaultConsole;
import de.elektroniker.hippocloud.lib.setup.Setup;
import java.io.File;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/******************************************************************
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 26.09.2019 / 21:53        
 *    Orginal Class: WrapperSetupConsole
 ******************************************************************/


public class WrapperSetupConsole implements Setup, SimpleFileWriter {

  private CloudLib cloudLib;
  private Class setupStepsClass;
  private File wrapperConfigFile = new File("./config.json");
  private Map<String, Object> setupAnswers = new HashMap<>();
  private Field questionField;
  private String questionMessage;

  @Override
  public UUID getUUID() {
    return UUID.randomUUID();
  }

  @Override
  public String getName() {
    return "WrapperSetup";
  }

  @Override
  public void hello() {
    log("Start Wrapper-Setup:");
    getNewQuestion();
  }

  @Override
  public void handle(String[] args) {
      if (setupStepsClass == null) {
          setupStepsClass = new WrapperSetupSteps().getClass();
      }

    StringBuilder stringBuilder = new StringBuilder();
    Arrays.stream(args).forEach(arg -> stringBuilder.append(arg + " "));
    String answer = stringBuilder.toString().trim();
    if (!setAnswer(questionField, answer)) {
      log("Invalid Input!");
      getNewQuestion();
    } else {
      setupAnswers.put(questionField.getName(), answer);
    }


  }

  private void getNewQuestion() {
    this.questionField = getQuestion(setupStepsClass).getKey();
    this.questionMessage = getQuestion(setupStepsClass).getValue();
    log("[SETUP] " + questionMessage + " <" + questionField.getType().getName() + ">");
  }

  @Override
  public void finishedSetup() {
    try {
      writeToFile(wrapperConfigFile, new Gson().toJson(setupAnswers), false);
    } catch (Exception e) {
      log("Error -> " + e.getMessage());
    }
    resetSetup();
    clearConsole();
    log("[SETUP] Finished Setup!");
    cloudLib.getConsoleRegistry().setConsole(new DefaultConsole());
  }

  @Override
  public Console setCloudLib(CloudLib cloudLib) {
    this.cloudLib = cloudLib;
    return this;
  }

  @Override
  public void sendHelp() {

  }


}
