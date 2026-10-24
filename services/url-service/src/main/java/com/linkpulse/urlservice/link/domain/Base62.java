package com.linkpulse.urlservice.link.domain;

public final class Base62 {
    private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final Integer BASE = ALPHABET.length();

    private Base62(){

    }

    public static String encode(long value){
        if(value < 0){
            throw new IllegalArgumentException("value must not be negative");
        }
        if(value == 0){
            return String.valueOf(ALPHABET.charAt(0));
        }
        StringBuilder result = new StringBuilder();
        while(value > 0){
            result.append(ALPHABET.charAt((int) (value % BASE)));
            value /= BASE;
        }
        return result.reverse().toString();
    }

    public static long decode(String text){
        if(text == null || text.isEmpty()){
            throw new IllegalArgumentException("text must not be empty");
        }
        long result = 0;
        for(char c: text.toCharArray()){
            int digit = ALPHABET.indexOf(c);
            if(digit < 0){
                throw new IllegalArgumentException("invalid base62 character: " + c);
            }
            result = Math.addExact(Math.multiplyExact(result, BASE), digit);
        }
        return result;
    }
}
