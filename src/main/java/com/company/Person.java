package com.company;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;


/**
 * 6. Person(человек), содержит Ф.И.О. (раздельно), дату рождения
 */


public class Person implements Serializable {
    private String lastName;
    private String firstName;
    private String middleName;
    private String fullName;
    private boolean fullNameChanged = true;
//    private LocalDate birthdate;
    private String birthdate;


    public Person(){
        setLastName("Иванов");
        setFirstName("Иван");
        setMiddleName("Иванович");
        setFullName();
        setBirthdate("10.01.2000");
    }

    public Person(String lastName, String firstName, String middleName,
                  String birthdate) {
        setLastName(lastName);
        setFirstName(firstName);
        setMiddleName(middleName);
        setFullName();
        setBirthdate(birthdate);
    }

    public Person(String lastName, String firstName,
                  String birthdate) {
        this(lastName, firstName, "", birthdate);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName == null) {
            throw new IllegalArgumentException("lastName == null");
        }
        if (lastName.length() == 0) {
            throw new IllegalArgumentException("lastName cannot be blank");
        }

        this.lastName = lastName;
//        fullNameChanged = true;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName == null) {
            throw new IllegalArgumentException("firstName == null");
        }
        if (firstName.length() == 0) {
            throw new IllegalArgumentException("firstName cannot be blank");
        }

        this.firstName = firstName;
        fullNameChanged = true;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        if (middleName == null) {
            throw new IllegalArgumentException("middleName == null");
        }

        this.middleName = middleName;
        fullNameChanged = true;
    }

    public String getFullName() {
        setFullName();
        return fullName;
    }

    private void setFullName() {
        if (fullNameChanged) {
            if (middleName.length() == 0) {
                fullName = String.format(
                        "%s %s", lastName, firstName);
            }
            else {
                fullName = String.format(
                        "%s %s %s", lastName, firstName, middleName);
            }

            fullNameChanged = false;
        }
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        if (birthdate == null) {
            throw new IllegalArgumentException("birthdate == null");
        }

        this.birthdate = birthdate;
    }

    public String getBirthdateString() {
        return birthdate.toString();
    }

    public void setBirthdateString(String birthdateString) {
        if (birthdateString == null || birthdateString.isEmpty()) {
            throw new IllegalArgumentException("birthdateString == null || birthdateString is empty");
        }

//        this.birthdate = LocalDate.parse(birthdateString);
        this.birthdate = birthdateString;
    }

//    public int getBirthdateDay() {
//        return birthdate.getDayOfMonth();
//    }

//    public int getBirthdateMonth() {
//        return birthdate.getMonthValue();
//    }

//    public int getBirthdateYear() {
//        return birthdate.getYear();
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Person person = (Person) o;
        return Objects.equals(lastName, person.lastName)
                && Objects.equals(firstName, person.firstName)
                && Objects.equals(middleName, person.middleName)
                && Objects.equals(birthdate, person.birthdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName, middleName, birthdate);
    }
}