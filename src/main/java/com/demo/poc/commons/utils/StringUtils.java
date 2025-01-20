package com.demo.poc.commons.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StringUtils {

    public static boolean isEmptyOrEquals(String baseField, String comparedField) {
        return isEmpty(baseField) || baseField.equals(comparedField);
    }

    private static boolean isEmpty(String field) {
        return field == null || field.isEmpty();
    }
}
