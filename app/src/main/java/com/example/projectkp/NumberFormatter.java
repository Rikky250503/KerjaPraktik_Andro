package com.example.projectkp;

import java.text.DecimalFormat;

public class NumberFormatter {
    public static String formatNumber(double number) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###.##");
        return decimalFormat.format(number);
    }
}
