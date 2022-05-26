package com.company;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReflectionDemo {
    /**
     * 1. Дан список объектов произвольных типов. Найдите количество элементов списка, которые
     * являются объектами типа Human или его подтипами.
     */

    public static int getNumInstanceOfHuman(List<Object> someList) {
        if (someList == null) throw new IllegalArgumentException("someList is null");
        if (someList.isEmpty()) throw new IllegalArgumentException("someList is empty");

        int count = 0;
        for (Object elem : someList) {
            if (elem instanceof Human) {
                count++;
            }
        }

        return count;
    }

    /**
     * 2. Для объекта получить список имен его открытых методов.
     */

    public static List<String> getListOfPublicMethods(Object obj) {
        if (obj == null) throw new IllegalArgumentException("obj is null");

        Class<?> classData = obj.getClass();
//        int mods = classData.getModifiers();
        Method[] methods = classData.getDeclaredMethods();
        List<String> methodNames = new ArrayList<>();
        for (Method method : methods) {
            if (Modifier.isPublic(method.getModifiers())) {
                methodNames.add(method.getName());
            }
        }

        return methodNames;
    }

    /**
     * 3. Для объекта получить список (в виде списка строк) имен всех его суперклассов до класса
     * Object включительно.
     */
    public static List<String> getListOfSuperclasses(Object obj) {
        if (obj == null) throw new IllegalArgumentException("obj is null");

        Class<?> classData = obj.getClass().getSuperclass();
//        String name = classData.getSuperclass().getName();

        List<String> superClassList = new ArrayList<>();
        while (classData != null) {

            superClassList.add(classData.getSimpleName());
            classData = classData.getSuperclass();
        }
        return superClassList;
    }

    /**
     * 5. *Для объекта получить список его геттеров и сеттеров (в виде списка строк).
     * Геттером считаем открытый нестатический метод без параметров, значение которого не void, а имя
     * начинается с get.
     * Сеттером считаем открытый нестатический метод с одним параметром, с
     * результатом типа void, а имя метода начинается с set.
     */

    public static List<String> getListOfGettersAndSetters(Object obj) {
        if (obj == null) throw new IllegalArgumentException("obj is null");

        List<String> listOfGettersAndSetters = new ArrayList<>();

        Class<?> classData = obj.getClass();
        Method[] methods = classData.getDeclaredMethods();
        for (Method method : methods) {
            //checking for getters
            if (Modifier.isPublic(method.getModifiers())
                    && !(Modifier.isStatic(method.getModifiers()))
                    && (method.getParameterCount() == 0)
                    && !(method.getReturnType().equals(Void.TYPE))
                    && (method.getName().startsWith("get"))) {
                listOfGettersAndSetters.add(method.getName());
            }
            //checking for setters
            if (Modifier.isPublic(method.getModifiers())
                    && !(Modifier.isStatic(method.getModifiers()))
                    && (method.getParameterCount() == 1)
                    && (method.getReturnType().equals(Void.TYPE))
                    && (method.getName().startsWith("set"))) {
                listOfGettersAndSetters.add(method.getName());
            }
        }

        return listOfGettersAndSetters;
    }
}



//TODO: тесты на 5*
