package kacper.book_tracker.util;

import org.springframework.security.core.parameters.P;

import java.util.ArrayList;
import java.util.List;

public class FilterUtils {
    
    public static Object castToRequiredType(Class<?> fieldType, String value) {
        if (fieldType.isAssignableFrom(Double.class)) {
            return Double.valueOf(value);
        } else if (fieldType.isAssignableFrom(Integer.class)) {
            return Integer.valueOf(value);
        } else if (fieldType.isAssignableFrom(Long.class)) {
            return Long.valueOf(value);
        } else if (fieldType.isAssignableFrom(Boolean.class)) {
            return Boolean.valueOf(value);
        } else if (Enum.class.isAssignableFrom(fieldType)) {
            return Enum.valueOf((Class<Enum>) fieldType, value);
        }
        return value; // default as String;
    }

    public static List<Object> castToRequiredType(Class<?> fieldType, List<String> values) {
        List<Object> result = new ArrayList<>();

        for (String val: values) {
            result.add(castToRequiredType(fieldType, val));
        }
        return result;
    }
}
