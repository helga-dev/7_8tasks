package com.company;



import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

/**
 * 6. House(дом), содержит кадастровый номер дома (строка),
 * адрес, старшего по дому(человек), список квартир
 */

// Надо будет закоментить аннотации для обычного data binding


public class House implements Serializable {
    private String cadastralNumber;
    private String address;
    private Person houseOwner;
    private final List<Flat> flats;


    public House(){
        setCadastralNumber("12345");
        setAddress("г. Омск, пр-кт Мира, д. 55а");
        Person p1 = new Person("Колотушкин", "А.", "С.",
             "   LocalDate.of(2001, 10, 22)");
        setHouseOwner(p1);


        Flat flat1 = new Flat(1, 50, p1);
        this.flats = new ArrayList<>();
        flats.add(flat1);
        createFlats(flats);
    }

    public House(String cadastralNumber,
                String address,
                 Person houseOwner,
                  List<Flat> flats) {
        setCadastralNumber(cadastralNumber);
        setAddress(address);
        setHouseOwner(houseOwner);

        this.flats = new ArrayList<>();
        createFlats(flats);
    }



    public House(String cadastralNumber, String address,
                 Person houseOwner, Flat ... flats) {
        this(cadastralNumber, address, houseOwner, Arrays.asList(flats));
    }

    public String getCadastralNumber() {
        return cadastralNumber;
    }

    public void setCadastralNumber(String cadastralNumber) {
        if (cadastralNumber == null) {
            throw new IllegalArgumentException("cadastralNumber == null");
        }

        this.cadastralNumber = cadastralNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address == null) {
            throw new IllegalArgumentException("address == null");
        }

        this.address = address;
    }

    public Person getHouseOwner() {
        return houseOwner;
    }

    public void setHouseOwner(Person houseOwner) {
        if (houseOwner == null) {
            throw new IllegalArgumentException("bossOfTheHouse == null");
        }

        this.houseOwner = houseOwner;
    }

    public List<Flat> getFlats() {
        return Collections.unmodifiableList(flats);
    }

    public void createFlats(List<Flat> flats) {
        if (flats == null) {
            throw new IllegalArgumentException("flats == null");
        }

        for (Flat flat : flats) {
            if (flat != null && !this.flats.contains(flat)) {
                this.flats.add(flat);
            }
        }
    }

    public void createFlats(Flat ... flats) {
        createFlats(Arrays.asList(flats));
    }

    public void removeFlats(List<Flat> flats) {
        if (flats == null) {
            throw new IllegalArgumentException("flats == null");
        }

        for (Flat flat : flats) {
            if (flat != null) {
                this.flats.remove(flat);
            }
        }
    }

    public void removeFlats(Flat ... flats) {
        removeFlats(Arrays.asList(flats));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        House house = (House) o;
        return Objects.equals(cadastralNumber, house.cadastralNumber)
                && Objects.equals(address, house.address)
                && Objects.equals(houseOwner, house.houseOwner)
                && Objects.equals(flats, house.flats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cadastralNumber, address, houseOwner, flats);
    }

    @Override
    public String toString() {
        return "House{" +
                "cadastralNumber='" + cadastralNumber + '\'' +
                ", address='" + address + '\'' +
                ", houseOwner=" + houseOwner +
                ", flats=" + flats +
                '}';
    }
}
