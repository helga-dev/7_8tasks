package com.company;


import com.fasterxml.jackson.databind.introspect.TypeResolutionContext;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.company.ReflectionDemo.*;

public class ReflectionDemoTest {
    @Test(expected = IllegalArgumentException.class)
    public void testNullListGetNumInstanceOfHuman(){
        getNumInstanceOfHuman(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyListGetNumInstanceOfHuman(){
        List<Object> someList = new ArrayList<>();
        getNumInstanceOfHuman(someList);

    }

    @Test
    public void testGetNumInstanceOfHuman(){
        List<Object> someList = new ArrayList<>();
        Human h1 = new Human();
        Human h2 = new Human();
        Student s1 = new Student();
        Person p1 = new Person();
        Collections.addAll(someList, h1, h2, s1, p1);

        Assertions.assertEquals(3, getNumInstanceOfHuman(someList));

    }
    @Test
    public void testGetNumInstanceOfHumanZero(){
        List<Object> someList = new ArrayList<>();

        Person p1 = new Person();
        Person p2 = new Person();
        Flat f1 = new Flat();
        House h1 = new House();
        Collections.addAll(someList, p1, p2, f1, h1);

        Assertions.assertEquals(0, getNumInstanceOfHuman(someList));

    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetListOfPublicMethodsNullObj(){
        getListOfPublicMethods(null);
    }

    @Test
    public void testGetListOfPublicMethods(){
        List<String> expected = new ArrayList<>();
        Collections.addAll(expected,"equals", "hashCode", "getFaculty", "setFaculty");
        Student s1 = new Student();
        List<String> actual = getListOfPublicMethods(s1);
        actual.sort(String::compareTo);
        expected.sort(String::compareTo);
        Assertions.assertEquals(expected, actual);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetListOfSuperclassesNullObj(){
        getListOfSuperclasses(null);
    }

    @Test
    public void testGetListOfSuperclasses(){
        Student s = new Student();
        List<String> expected = new ArrayList<>();
        Collections.addAll(expected, "Object", "Human");
        expected.sort(String::compareTo);
        List<String> actual = getListOfSuperclasses(s);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testGetListOfSuperclassesOfObject(){
        List<String> expected = new ArrayList<>();
        Object obj = new Object();
        List<String> actual = getListOfSuperclasses(obj);
        Assertions.assertEquals(expected, actual);
    }

}
