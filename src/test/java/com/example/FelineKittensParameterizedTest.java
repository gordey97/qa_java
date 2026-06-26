package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class FelineKittensParameterizedTest {

    private final int kittensCount;

    public FelineKittensParameterizedTest(int kittensCount) {
        this.kittensCount = kittensCount;
    }

    @Parameterized.Parameters(name = "Тестовые данные: {0}")
    public static Object[][] data() {
        return new Object[][]{
                {0},
                {1},
                {3}
        };
    }

    @Test
    public void getKittensWithArgumentReturnsPassedValue() {
        Feline feline = new Feline();

        assertEquals(kittensCount, feline.getKittens(kittensCount));
    }
}
