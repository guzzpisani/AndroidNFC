package com.example.cl1324.myapplication.util.Hex;

/**
 * Created by cl1324 on 01/11/2016.
 */

public class Hex {
    public static String hexToString(byte[] array) {
        StringBuilder stringBuilder = new StringBuilder();
        if (array == null || array.length <= 0) {
            return null;
        }

        char[] buffer = new char[2];
        for (int i = 0; i < array.length; i++) {
            buffer[0] = Character.forDigit((array[i] >>> 4) & 0x0F, 16);
            buffer[1] = Character.forDigit(array[i] & 0x0F, 16);
            stringBuilder.append(buffer);
        }
        return stringBuilder.toString();
    }
}
