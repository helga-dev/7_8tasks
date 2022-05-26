package com.company;

import java.util.Objects;

public class Human {
    private String surname, name, middleName;
    private int age;

    public Human(){
        this.surname = "Иванова";
        this.name = "Мария";
        this.middleName = "Петровна";
        this.age = 20;
    }

    public Human(String surname, String name, String middleName, int age){
        setSurname(surname);
        setName(name);
        setMiddleName(middleName);
        setAge(age);
    }

    public String getName() {
        return name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        if(name != null && !name.equals("")) {
            this.name = name;
        }
        else {
            throw new IllegalArgumentException("check args");
        }
    }

    public void setMiddleName(String middleName) {
        if (middleName != null && !middleName.equals("")) {
            this.middleName = middleName;
        }
        else {
            throw new IllegalArgumentException("check args");
        }
    }

    public void setSurname(String surname) {
        if (surname != null && !surname.equals("")) {
            this.surname = surname;
        }
        else{
            throw new IllegalArgumentException("check args");
        }
    }

    public void setAge(int age) {
        if ( age >= 0) {
            this.age = age;
        }
        else {
            throw new IllegalArgumentException("check args");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Human)) return false;
        Human human = (Human) o;
        return age == human.age && Objects.equals(surname, human.surname) && Objects.equals(name, human.name) && Objects.equals(middleName, human.middleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(surname, name, middleName, age);
    }

    @Override
    public String toString() {
        return "Human{" +
                "surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", middleName='" + middleName + '\'' +
                ", age=" + age +
                '}';
    }
}