package com.company;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.*;

import static com.company.Streams.*;
import static org.junit.jupiter.api.Assertions.*;


public class StreamsTest {
    private static File testsDir;

    @BeforeAll
    public static void createTestsDir() {
        testsDir = new File("tests/TestStreamAndFileDemo/");
        testsDir.mkdirs();
    }


    @Test
    public void testNullStreamWriteIntArrayBinary() throws IOException {

        int[] arrayInt = {500000000, 1, 29, -2, -1002};
        assertThrows(IllegalArgumentException.class,
                () ->writeIntArrayBinary(null, arrayInt));

    }

    @Test
    public void testNullArrayWriteIntArrayBinary() throws IOException {
        byte[] byteArrBuf;

        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            assertThrows(IllegalArgumentException.class,
                    () ->writeIntArrayBinary(out, null));
            byteArrBuf = out.toByteArray();
        }
    }

    @Test
    public void testNullStreamReadIntArrayBinary() throws IOException {
        assertThrows(IllegalArgumentException.class,
                () ->
            readIntArrayBinary(null, 5));

    }

    @Test
    public void testNegativeNumReadIntArrayBinary() throws IOException {
//        int[] expected = {500000000, 1, 29, -2, 1000, -7};

        byte[] byteArrBuf = {0,0,0,1, 10,10,123,123};

//        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
//            writeIntArrayBinary(out, expected);
//
//            byteArrBuf = out.toByteArray();
//        }
//
//        int[] actual;

        try (ByteArrayInputStream in = new ByteArrayInputStream(byteArrBuf)) {
            assertThrows(IllegalArgumentException.class,
                    () ->readIntArrayBinary(in, -5));
        }
    }

    @Test
    public void testWriteIntArrayBinary() throws IOException {
//        int[] expected = {500000000, 1, 29, -2, 1000, -7};

        int[] array = {123, -123, 2, 4};
        byte[] expected = {0,0,0,123, -1,-1,-1,-123, 0,0,0,2, 0,0,0,4};

        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            writeIntArrayBinary(out, array);

            assertArrayEquals(expected, out.toByteArray());
        } catch (Exception e) {
            fail();
        }

//        int[] actual;
//
//        try (ByteArrayInputStream in = new ByteArrayInputStream(byteArrBuf)) {
//            actual = readIntArrayBinary(in, expected.length);
//        }
//
//        assertArrayEquals(expected, actual);
    }

    @Test
    public void testReadIntArrayBinary() throws IOException {
        byte[] byteArrBuf = {0,0,0,123, -1,-1,-1,-123, 0,0,0,2, 0,0,0,4};

        int[] expected = {123, -123, 2, 4};

//        byte[] byteArrBuf;

//        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
//            writeIntArrayBinary(out, expected);
//
//            byteArrBuf = out.toByteArray();
//        }

        int[] actual;

        try (ByteArrayInputStream in = new ByteArrayInputStream(byteArrBuf)) {
            actual = readIntArrayBinary(in, expected.length);
            assertArrayEquals(expected, actual);
        } catch (Exception e) {
            fail();
        }

//        assertArrayEquals(expected, actual);
    }


    @Test
    public void testWriteReadIntArrayChar() throws IOException {
        int[] expected = {-123456, -1, 44, 0, -55, 2048};

        char[] charArrBuf;

        try (CharArrayWriter out = new CharArrayWriter()) {
            writeIntArrayChar(out, expected);

            charArrBuf = out.toCharArray();
        }

        int[] actual;

        try (CharArrayReader in = new CharArrayReader(charArrBuf)) {
            actual = readIntArrayChar(in, expected.length);
        }

       assertArrayEquals(expected, actual);
    }


    @Test
    public void testReadIntArrayFromFile() throws IOException {
        File file = new File(testsDir, "testReadIntArrayFromFile");
        file.createNewFile();

        int[] writeArray = {101, 31415, 2, 3, -228};
        int[] expected = {2, 3, -228};

        try (DataOutputStream out = new DataOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(file)))) {
            for (int i = 0; i < writeArray.length; ++i) {
                out.writeInt(writeArray[i]);
            }
        }

        int[] actual;

        try (var randomAccessFile = new RandomAccessFile(file, "r")) {
            actual = readIntArrayFromFile(randomAccessFile, 8);
        }
        assertArrayEquals(expected, actual);
    }


    @Test
    public void testListOfFilesInDir() throws IOException {
        File currTestDir = new File(testsDir, "testListOfFilesInDirWithExtension/");
        currTestDir.mkdirs();

        File file1 = new File(currTestDir, "HelloWorld.c");
        file1.createNewFile();
        File file2 = new File(currTestDir, "HelloWorld.py");
        file2.createNewFile();
        File file3 = new File(currTestDir, "funny_gif_with_cute_cats.gif");
        file3.createNewFile();

        File dir1 = new File(currTestDir, "dir1/");
        dir1.mkdirs();
        File dir2 = new File(currTestDir, "anime_girls_pictures/");
        dir2.mkdirs();

        List<File> expected = Arrays.asList(file1);
        List<File> actual = listOfFilesInDirWithExtension(currTestDir, "c");

        expected.sort(Comparator.comparing(File::getName));
        actual.sort(Comparator.comparing(File::getName));

        assertEquals(expected, actual);
    }


}


