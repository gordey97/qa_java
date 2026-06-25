package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

@RunWith(Parameterized.class)
public class LionManeParameterizedTest {

    private final String sex;
    private final boolean hasMane;

    public LionManeParameterizedTest(String sex, boolean hasMane) {
        this.sex = sex;
        this.hasMane = hasMane;
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][]{
                {"Самец", true},
                {"Самка", false}
        };
    }

    @Test
    public void doesHaveManeReturnsExpectedValue() throws Exception {
        Lion lion = new Lion(sex, mock(Feline.class));
        assertEquals(hasMane, lion.doesHaveMane());
    }
}
