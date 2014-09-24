package com.github.greengerong.book.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * ***************************************
 * *
 * Auth: green gerong                     *
 * Date: 2014                             *
 * blog: http://greengerong.github.io/    *
 * github: https://github.com/greengerong *
 * *
 * ****************************************
 */
public class LogUtils {
    public static String getStackTrace(Throwable throwable) {
        final StringWriter sw = new StringWriter();
        throwable.printStackTrace(new PrintWriter(sw));
        sw.flush();
        return sw.toString();
    }
}
