package com.rarait.education.utility;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */

public final class BaseConversion {

    private BaseConversion() {
    }

    public static String getCode(long number, String format) {
        String base36;
        if (number < 0) {
            base36 = "";
        } else {
            base36 = Long.toString(number, 36);
        }
        return String.format(format.isEmpty() ? "%7S" : format, base36).replace(' ', '0');
    }

    public static String getCode(long number) {
        return getCode(number, "");
    }

    public static long getLong(String code) {
        return Long.parseLong(code, 36);
    }

    public static int getInteger(String code) {
//        return Integer.parseInt(code, 36);
        return Integer.valueOf(String.valueOf(getLong(code)));
    }
}
