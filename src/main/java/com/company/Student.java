package com.company;

import java.util.Objects;

public class Student extends Human {
    private String faculty;

    public Student() {
        super();
        faculty = "ИМИТ";
    }

    public Student(String surname, String name, String middleName, int age, String faculty) {
        super(surname, name, middleName, age);
        setFaculty(faculty);
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        if (faculty != null && !faculty.equals("")) {
            this.faculty = faculty;
        } else {
            throw new IllegalArgumentException("String faculty should be not null and not empty");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return Objects.equals(faculty, student.faculty);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), faculty);
    }
}