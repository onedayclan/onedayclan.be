package com.clanone.onedayclan.member.application.service.util;

public class NumberUtil {
    public static String generateAuthorizationNumber(){
        java.util.Random generator = new java.util.Random();
        generator.setSeed(System.currentTimeMillis());
        return String.valueOf(generator.nextInt(1000000) % 1000000);
    }
}
