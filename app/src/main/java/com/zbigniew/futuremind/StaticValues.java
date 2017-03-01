package com.zbigniew.futuremind;

import java.util.regex.Pattern;

/**
 * Created by zbigniew on 28/02/2017.
 */

public class StaticValues {

    public static final String BASE_URL = "http://pinky.futuremind.com/~dpaluch/";
    public static final Pattern URL_PATTERN = Pattern.compile(
            "(?:^|[\\W])((ht|f)tp(s?):\\/\\/|www\\.)"
                    + "(([\\w\\-]+\\.){1,}?([\\w\\-.~]+\\/?)*"
                    + "[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};']*)",
            Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
}
