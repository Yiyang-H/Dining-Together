package com.summerHack.diningTogether.utils;

import org.springframework.lang.Nullable;

import java.util.Base64;

public class Base64Utils {
    /**
     * Decode base64 to byte array. If input is null, return null
     *
     * @param base64
     * @return
     */
    public static byte[] base64ToByteArray(@Nullable String base64) {
        if (base64 == null) {
            return null;
        }
        return Base64.getDecoder().decode(base64);
    }

    /**
     * Encode byte array into base64 string. If input is null, return null
     *
     * @param array
     * @return
     */
    public static String byteArrayToBase64(@Nullable byte[] array) {
        if (array == null) {
            return null;
        }
        return Base64.getEncoder().encodeToString(array);
    }
}
