package com.oswizar.springbootsample.util;

import java.security.SecureRandom;

public class UnidUtils {

    public static String getUNID() {
        SecureRandom seeder = new SecureRandom();
        StringBuffer buf = new StringBuffer();
        long time = System.currentTimeMillis();
        int timeLow = (int)time & -1;
        int node = seeder.nextInt();
        buf.append(hexFormat(timeLow, 8)).append(hexFormat(node, 8));
        return buf.toString();
    }
    private static String hexFormat(int number, int digits) {
        String hex = Integer.toHexString(number).toUpperCase();
        if(hex.length() >= digits) {
            return hex.substring(0, digits);
        } else {
            int padding = digits - hex.length();
            StringBuffer buf = new StringBuffer();
            for(int i = 0; i < padding; ++i) {
                buf.append("0");
            }
            buf.append(hex);
            return buf.toString();
        }
    }


    public static void main(String[] args) {
        String unid = getUNID();
        System.out.println(unid);
    }
}
