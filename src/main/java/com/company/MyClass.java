package com.company;

import java.util.Objects;

public class MyClass implements IExecutable{
    private boolean isHomeworkDone;

    public MyClass(){
        isHomeworkDone = false;
    }
    public MyClass(boolean isHomeworkDone){

        this.isHomeworkDone = isHomeworkDone;
    }

    public boolean doHomework(){
        boolean isHomeworkDone = true;
        return isHomeworkDone;
    }

    @Override
    public void execute() {
        doHomework();
    }

    public boolean isHomeworkDone() {
        return isHomeworkDone;
    }

    public void setHomeworkDone(boolean homeworkDone) {
        isHomeworkDone = homeworkDone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyClass myClass = (MyClass) o;
        return isHomeworkDone == myClass.isHomeworkDone;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isHomeworkDone);
    }
}
