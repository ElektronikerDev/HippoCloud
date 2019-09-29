package de.elektroniker.hippocloud.lib.setup;

import de.elektroniker.hippocloud.lib.console.Console;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/******************************************************************
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 26.09.2019 / 21:55        
 *    Orginal Class: Setup
 ******************************************************************/


public interface Setup extends Console {
    Map<Field, Object> answers = new LinkedHashMap<>();
    Map<Field, String> questions = new LinkedHashMap<>();
    Map<Field, String[]> invalidInputs = new LinkedHashMap<>();

    default boolean isFinished(Field field) {
        if (answers.get(field) == null) {
            return false;
        } else {
            return true;
        }
    }

    default Object getAnswer(Field field) {
        if (answers.get(field) == null) return null;
        return answers.get(field);
    }
    default Object getAnswer(String fieldName) {
        Field field = answers.keySet().stream().filter(fld -> fld.getName().equals(fieldName)).findFirst().orElse(null);
        if (answers.get(field) == null) return null;
        return answers.get(field);
    }

    default boolean setAnswer(Field field, Object value) {
        if(value.getClass().getSimpleName() != field.getType().getSimpleName()) return false;
        Step step = field.getAnnotation(Step.class);
        for (String invalid : step.invalidInputs()) if (value.toString().contains(invalid)) return false;

        if (answers.containsKey(field)) answers.remove(field);
        answers.put(field, value);

        if(getOpenQuestions(field.getDeclaringClass()).keySet().size()==0){
            finishedSetup();
        }

        return true;
    }

    default HashMap<Field, String> getQuestions(Class clazz) {
        if (questions.size() == 0) Arrays.stream(clazz.getFields()).filter(field -> field.getAnnotation(Step.class) != null).forEach(this::insertQuestion);
        return new HashMap<>(questions);
    }
    default HashMap<Field, String> getOpenQuestions(Class clazz) {
        if (questions.size() == 0) Arrays.stream(clazz.getFields()).filter(field -> field.getAnnotation(Step.class) != null).forEach(this::insertQuestion);
        HashMap<Field, String> openQuestions = new HashMap<>();
        questions.forEach((field, question) -> { if((!answers.containsKey(field))) openQuestions.put(field, question); });
        return new HashMap<>(openQuestions);
    }

    default Map.Entry<Field, String> getQuestion(Class clazz){
        Map<Field,String> map = getOpenQuestions(clazz);
        Map.Entry<Field,String> entry = map.entrySet().iterator().next();
        return entry;
    }

    default void insertQuestion(Field field) {
        Step step = field.getAnnotation(Step.class);
        questions.put(field, step.message());
        invalidInputs.put(field, step.invalidInputs());
    }

    default void resetSetup(){
        answers.clear();
        questions.clear();
        invalidInputs.clear();
    }


    void finishedSetup();


}
