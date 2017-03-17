package com.vb.mafia.core.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public abstract class ExceptionUtil {

    public static String getStackTraceInfo(Throwable t)
    {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        try {
            t.printStackTrace(pw);
            return sw.toString();
        } finally {
            pw.close();
        }
    }

    public static String getStackTraceShortInfo(Throwable t)
    {
        return String.valueOf(t.getStackTrace()[0]);
    }
}
