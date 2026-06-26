package com.example;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class AnimalTest {

    private final Animal animal = new Animal();

    @Test
    public void getFoodReturnsHerbivoreFood() throws Exception {
        assertEquals(List.of("Трава", "Различные растения"), animal.getFood("Травоядное"));
    }

    @Test
    public void getFoodReturnsPredatorFood() throws Exception {
        assertEquals(List.of("Животные", "Птицы", "Рыба"), animal.getFood("Хищник"));
    }

    @Test(expected = Exception.class)
    public void getFoodThrowsExceptionForUnknownAnimalKind() throws Exception {
        animal.getFood("Всеядное");
    }

    @Test
    public void getFamilyReturnsFamilies() {
        assertEquals(
                "Существует несколько семейств: заячьи, беличьи, мышиные, кошачьи, псовые, медвежьи, куньи",
                animal.getFamily()
        );
    }
}
