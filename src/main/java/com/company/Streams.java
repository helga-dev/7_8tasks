package com.company;

import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Streams {
    /**
     * 1. Записать массив целых чисел в двоичный поток.
     * Прочитать массив целых чисел из двоичного потока.
     * Предполагается, что до чтения массив уже создан,
     * нужно прочитать n чисел, где n—длина массива.
     */
    public static void writeIntArrayBinary(OutputStream outputStream, int[] array) throws IOException {

        if (outputStream == null) {
            throw new IllegalArgumentException("outputStream == null");
        }

        if (array == null) {
            throw new IllegalArgumentException("array == null");
        }

        //буферизированный поток эффективнее
        try (DataOutputStream out = new DataOutputStream(new BufferedOutputStream(outputStream))) {
            for (int j : array) {
                out.writeInt(j);
            }
        }
    }

    /**
     * 1. Записать массив целых чисел в двоичный поток.
     * Прочитать массив целых чисел из двоичного потока.
     * Предполагается, что до чтения массив уже создан,
     * нужно прочитать n чисел, где n—длина массива.
     */
    public static int[] readIntArrayBinary(InputStream inputStream, int n) throws IOException {
        if (inputStream == null) {
            throw new IllegalArgumentException("inputStream == null");
        }
        if (n < 0) {
            throw new IllegalArgumentException("n < 0");
        }

        int[] array = new int[n];

        try (DataInputStream in = new DataInputStream(new BufferedInputStream(inputStream))) {
            for (int i = 0; i < array.length; ++i) {
                array[i] = in.readInt();
            }
        }

        return array;
    }


    /**
     * 2. Аналогично, используя символьные потоки.
     * В потоке числа должны разделяться пробелами.
     */
    public static void writeIntArrayChar(Writer writer, int[] array) {
        if (writer == null) {
            throw new IllegalArgumentException("writer == null");
        }
        if (array == null) {
            throw new IllegalArgumentException("array == null");
        }

        try (PrintWriter out = new PrintWriter(new BufferedWriter(writer))) {
            for (int i = 0; i < array.length; ++i) {
                out.print(array[i]);
                out.print(' ');
            }

            out.println();
        }
    }

    /**
     * 2. Аналогично, используя символьные потоки.
     * В потоке числа должны разделяться пробелами.
     */
    public static int[] readIntArrayChar(Reader reader, int n) throws IOException {
        if (reader == null) {
            throw new IllegalArgumentException("reader == null");
        }
        if (n < 0) {
            throw new IllegalArgumentException("n < 0");
        }

        int[] array = new int[n];

        try (BufferedReader in = new BufferedReader(reader)) {
            String[] strings = in.readLine().split(" ");

            if (strings.length < array.length) {
                throw new IOException("not enough ints to read");
            }

            for (int i = 0; i < array.length; ++i) {
                array[i] = Integer.parseInt(strings[i]);
            }
        }

        return array;
    }

    /**
     * 3. Используя класс RandomAccessFile,
     * прочитайте массив целых чисел, начиная с заданной позиции.
     */
    public static int[] readIntArrayFromFile(RandomAccessFile file, long pos) throws IOException {
        if (file == null) {
            throw new IllegalArgumentException("file == null");
        }
        if (pos < 0) {
            throw new IllegalArgumentException("pos < 0");
        }

        file.seek(pos);

        int[] array = new int[(int) ((file.length() - pos) / Integer.BYTES)];

        for (int i = 0; i < array.length; ++i) {
            array[i] = file.readInt();
        }

        return array;
    }


    /**
     * 4. Используя класс File, получите список всех файлов
     * с заданным расширением в заданном каталоге
     * (поиск в подкаталогах выполнять не надо).
     */
    public static List<File> listOfFilesInDirWithExtension(
            File file, String extension) {
        if (file == null || extension == null) {
            throw new IllegalArgumentException(
                    "file == null || extension == null");
        }

        String regex;

        if (extension.length() == 0) {
            regex = "^([.]?)([^.]*)$";
        }
        else {
            regex = "^([.]?)([^.]*)[.]" + extension + "$";
        }

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher("");

        File[] fileArray = file.listFiles();

        if (fileArray == null) {
            throw new IllegalArgumentException("file is not a dir");
        }

        List<File> files = new ArrayList<>();

        for (int i = 0; i < fileArray.length; ++i) {
            if (fileArray[i].isFile()) {
                matcher.reset(fileArray[i].getName());

                if (matcher.matches()) {
                    files.add(fileArray[i]);
                }
            }
        }

        return files;
    }


}