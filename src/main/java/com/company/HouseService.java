package com.company;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;


public class HouseService {
    /**
     * 6. Напишите сервисный класс с методами, которые сериализуют
     * и десериализуют объект типа House в заданный поток средствами Java.
     */
    public static void serializeHouse(OutputStream outputStream, House house) throws IOException {
        if (outputStream == null || house == null) {
            throw new IllegalArgumentException("outputStream == null || house == null");
        }

        try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(outputStream))) {
            out.writeObject(house);
        }
    }

    /**
     * 6. Напишите сервисный класс с методами, которые сериализуют
     * и десериализуют объект типа House в заданный поток средствами Java.
     */
    public static House deserializeHouse(InputStream inputStream) throws IOException, ClassNotFoundException {
        if (inputStream == null) {
            throw new IllegalArgumentException("inputStream == null");
        }

        try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(inputStream))) {
            return (House) in.readObject();
        }
    }

    /**
     * 8.Подключите к проекту библиотеку Jackson. Напишите методы сериализации /
     * десериализации объектов типа House в строки. Используйте data binding.
     */
    public static String serializeHouseToJson(House house)
            throws IOException {
        if (house == null) {
            throw new IllegalArgumentException("house == null");
        }

        return new ObjectMapper().writeValueAsString(house);
    }

    /**
     * 8.Подключите к проекту библиотеку Jackson. Напишите методы сериализации /
     * десериализации объектов типа House в строки. Используйте data binding.
     */
    public static House deserializeHouseFromJson(String houseJson)
            throws IOException {
        if (houseJson == null) {
            throw new IllegalArgumentException("houseJson == null");
        }

        return new ObjectMapper().readValue(houseJson, House.class);
    }




}