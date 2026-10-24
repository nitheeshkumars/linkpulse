package com.linkpulse.urlservice.link.domain;

public final class ShortCode {
    public static final String REGEX = "[0-9A-Za-z_-]{3,20}";
    public static final Integer MAX_LENGTH = 20;
    private ShortCode(){

    }
}