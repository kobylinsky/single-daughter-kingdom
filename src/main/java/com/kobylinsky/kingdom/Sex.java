package com.kobylinsky.kingdom;

import java.util.Random;

public enum Sex {

    M, // Male
    F; // Female

    private static final Random RND = new Random();

    public static Sex random() {
        if (RND.nextBoolean()) {
            return M;
        } else {
            return F;
        }
    }

}