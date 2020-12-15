package web.utils;

public class isEmptyStr {
    public static boolean isEmpty(String string) {
        return !(null != string && string.length() > 0 && !"null".equals(string));
    }

    public static boolean isNotEmpty(String string) {
        return !isEmpty(string);
    }
}
