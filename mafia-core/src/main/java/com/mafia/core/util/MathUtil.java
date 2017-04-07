package com.mafia.core.util;

import java.math.BigDecimal;

public abstract class MathUtil {

    public static boolean isIntInRange(int i, int iFrom, int iTo)
    {
        return i >= iFrom && i <= iTo;
    }

    public static Double plus(Double d1, Double d2)
    {
        BigDecimal bd1 = trans2BigDecimal(d1);
        BigDecimal bd2 = trans2BigDecimal(d2);
        return bd1.add(bd2).doubleValue();
    }

    public static Double minus(Double d1, Double d2)
    {
        BigDecimal bd1 = trans2BigDecimal(d1);
        BigDecimal bd2 = trans2BigDecimal(d2);
        return bd1.subtract(bd2).doubleValue();
    }

    private static BigDecimal trans2BigDecimal(Double d)
    {
        return d == null ? new BigDecimal(0) : new BigDecimal(d);
    }
}
