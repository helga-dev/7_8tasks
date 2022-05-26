package com.company;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.time.LocalDate;

import static com.company.HouseService.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class TestHouseService {
    private static File testsDir;

    private static Person p1;
    private static Person p2;
    private static Person p3;
    private static Person p4;

    private static Flat flat1;
    private static Flat flat2;
    private static Flat flat3;

    private static House house;

    @BeforeAll
    public static void createTestsDir() {
        testsDir = new File("tests/TestHouseService/");
        testsDir.mkdirs();
        p1 = new Person("Колотушкин", "А.", "С.", "LocalDate.of(2001, 10, 22)");
        p2 = new Person("Ларин", "Д.", "В.", "LocalDate.of(1987, 11, 29)");
        p3 = new Person("Хованский", "Ю.", "М.", "LocalDate.of(1990, 01, 19)");
        p4 = new Person("Star", "Patrick", "LocalDate.of(1100, 7, 7)");

        flat1 = new Flat(1, 50, p1);
        flat2 = new Flat(2, 120, p2, p3);
        flat3 = new Flat(3, 75, p4);

        house = new House("123456", "г. Омск, ул. Пушкина, д. Колотушкина", p1, flat1, flat2, flat3);
        System.out.println("house = " + house);
//        initAll();
    }


//    private static void initAll() {
//        p1 = new Person("Колотушкин", "А.", "С.", LocalDate.of(2001, 10, 22));
//        p2 = new Person("Ларин", "Д.", "В.", LocalDate.of(1987, 11, 29));
//        p3 = new Person("Хованский", "Ю.", "М.", LocalDate.of(1990, 01, 19));
//        p4 = new Person("Star", "Patrick", LocalDate.of(1100, 7, 7));
//
//        flat1 = new Flat(1, 50, p1);
//        flat2 = new Flat(2, 120, p2, p3);
//        flat3 = new Flat(3, 75, p4);
//
//        house = new House("123456", "г. Омск, ул. Пушкина, д. Колотушкина", p1, flat1, flat2, flat3);
//
//    }

    @Test
    public void testSerializeDeserializeHouse() throws IOException, ClassNotFoundException {

        assertNotNull(house);
        File serializedHouseFile = new File(testsDir, "serializedHouseFile");
        serializedHouseFile.createNewFile();

        try (FileOutputStream out = new FileOutputStream(serializedHouseFile)) {
            assertNotNull(out);
            serializeHouse(out, house);
        }

        House deserializedHouse;

        try (FileInputStream in = new FileInputStream(serializedHouseFile)) {
            deserializedHouse = deserializeHouse(in);
        }

        Assertions.assertEquals(house, deserializedHouse);
    }

    @Test
    public void testSerializeDeserializeHouseJsonDataBinding() throws IOException {
        Assertions.assertEquals(house, deserializeHouseFromJson(serializeHouseToJson(house)));
    }


}
