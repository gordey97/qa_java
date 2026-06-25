package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LionTest {

    @Test
    public void getKittensReturnsFelineKittensCount() throws Exception {
        Feline feline = mock(Feline.class);
        when(feline.getKittens()).thenReturn(2);
        Lion lion = new Lion("Самка", feline);

        assertEquals(2, lion.getKittens());
        verify(feline).getKittens();
    }

    @Test
    public void getFoodReturnsPredatorFoodFromFeline() throws Exception {
        Feline feline = mock(Feline.class);
        List<String> food = List.of("Животные", "Птицы", "Рыба");
        when(feline.getFood("Хищник")).thenReturn(food);
        Lion lion = new Lion("Самец", feline);

        assertEquals(food, lion.getFood());
        verify(feline).getFood("Хищник");
    }

    @Test
    public void constructorThrowsExceptionForUnknownSex() {
        Feline feline = mock(Feline.class);

        Exception exception = assertThrows(Exception.class, () -> new Lion("", feline));
        assertEquals("Используйте допустимые значения пола животного - самец или самка", exception.getMessage());
    }

}
