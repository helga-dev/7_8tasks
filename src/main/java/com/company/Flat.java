package com.company;


import java.io.Serializable;
import java.util.*;

/**
 * 6. Flat(квартира), содержит номер, площадь, данные о владельцах(список людей)
 */


public class Flat implements Serializable {
    private int flatNumber;
    private double flatAreaSqM;
    private final List<Person> owners;

    public Flat(){
        setFlatNumber(10);
        setFlatAreaSqM(100);

        this.owners = new ArrayList<>();
        addOwners(owners);


    }

    public Flat(int flatNumber,
               double flatAreaSqM,
               List<Person> owners) {
        setFlatNumber(flatNumber);
        setFlatAreaSqM(flatAreaSqM);

        this.owners = new ArrayList<>();
        addOwners(owners);
    }

    public Flat(int flatNumber, double flatAreaSqM, Person ... owners) {
        this(flatNumber, flatAreaSqM, Arrays.asList(owners));
    }

    public int getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(int flatNumber) {
        if (flatNumber < 1) {
            throw new IllegalArgumentException("flatNumber < 1");
        }

        this.flatNumber = flatNumber;
    }

    public double getFlatAreaSqM() {
        return flatAreaSqM;
    }

    public void setFlatAreaSqM(double flatAreaSqM) {
        if (flatAreaSqM <= 0) {
            throw new IllegalArgumentException("flatAreaSqM <= 0");
        }

        this.flatAreaSqM = flatAreaSqM;
    }

    public List<Person> getOwners() {
        return Collections.unmodifiableList(owners);
    }

    public void addOwners(List<Person> owners) {
        if (owners == null) {
            throw new IllegalArgumentException("owners == null");
        }

        for (Person owner : owners) {
            if (owner != null && !this.owners.contains(owner)) {
                this.owners.add(owner);
            }
        }
    }

    public void addOwners(Person ... owners) {
        addOwners(Arrays.asList(owners));
    }

    public void removeOwners(List<Person> owners) {
        if (owners == null) {
            throw new IllegalArgumentException("owners == null");
        }

        for (Person owner : owners) {
            if (owner != null) {
                this.owners.remove(owner);
            }
        }
    }

    public void removeOwners(Person ... owners) {
        removeOwners(Arrays.asList(owners));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Flat flat = (Flat) o;
        return flatNumber == flat.flatNumber
                && Double.compare(flatAreaSqM, flat.flatAreaSqM) == 0
                && Objects.equals(owners, flat.owners);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flatNumber, flatAreaSqM, owners);
    }
}
