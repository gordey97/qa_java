package com.example;

import java.util.List;

public class Feline extends Animal {

    public List<String> eatMeat() throws Exception {
        return getFood("Хищник");
    }

    public String getFamily() {
        return "Кошачьи";
    }

    public int getKittens() {
        return getKittens(1);
    }

    public int getKittens(int kittensCount) {
        return kittensCount;
    }
}
