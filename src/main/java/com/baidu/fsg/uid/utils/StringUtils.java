package com.baidu.fsg.uid.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述:
 *
 * @author kfyty725
 * @date 2023/7/29 23:19
 * @email kfyty725@hotmail.com
 */
public class StringUtils {
    public static final char PACKAGE_SEPARATOR_CHAR = '.';

    public static final char INNER_CLASS_SEPARATOR_CHAR = '$';

    private static final Map<Object, Object> reverseAbbreviationMap = new HashMap<>();

    public static boolean isBlank(String s) {
        return !isNotBlank(s);
    }

    public static boolean isNotBlank(String s) {
        return s != null && s.trim().length() > 0;
    }

    public static String getShortClassName(String className) {
        if (className == null) {
            return "";
        }
        if (className.length() == 0) {
            return "";
        }

        StringBuilder arrayPrefix = new StringBuilder();

        // Handle array encoding
        if (className.startsWith("[")) {
            while (className.charAt(0) == '[') {
                className = className.substring(1);
                arrayPrefix.append("[]");
            }
            // Strip Object type encoding
            if (className.charAt(0) == 'L' && className.charAt(className.length() - 1) == ';') {
                className = className.substring(1, className.length() - 1);
            }
        }

        if (reverseAbbreviationMap.containsKey(className)) {
            className = (String) reverseAbbreviationMap.get(className);
        }

        int lastDotIdx = className.lastIndexOf(PACKAGE_SEPARATOR_CHAR);
        int innerIdx = className.indexOf(INNER_CLASS_SEPARATOR_CHAR, lastDotIdx == -1 ? 0 : lastDotIdx + 1);
        String out = className.substring(lastDotIdx + 1);
        if (innerIdx != -1) {
            out = out.replace(INNER_CLASS_SEPARATOR_CHAR, PACKAGE_SEPARATOR_CHAR);
        }
        return out + arrayPrefix;
    }
}
