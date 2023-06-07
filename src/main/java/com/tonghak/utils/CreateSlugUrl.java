package com.tonghak.utils;

import java.util.StringJoiner;

public class CreateSlugUrl {
    public static String urlSlug(String title) {

        String slug = new StringJoiner("-").add(title.toLowerCase().replaceAll("[^a-zA-Z0-9]", "-")).toString(); 
        return slug;
    }
}
