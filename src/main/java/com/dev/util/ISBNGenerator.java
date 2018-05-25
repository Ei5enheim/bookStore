package com.dev.util;

import java.util.Random;

public class ISBNGenerator implements NumGenerator {
    public String genNumber () {
        return "13-5677-" + Math.abs(new Random().nextInt());
    }
}
