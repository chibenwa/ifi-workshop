package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

public class Cata1
{
    // Rewrite me with the stream API
    public double norm(List<Integer> input) {
        int sumOfSquares = 0;
        for(int i: input) {
            sumOfSquares += i * i;
        }
        return Math.sqrt(sumOfSquares);
    }

    @Test
    public void exercice1() {
        assertEquals(norm(List.of(1,2,3)), 3.7416573867739413d );
        assertEquals(norm(List.of(2)), 2d );
        assertEquals(norm(List.of()), 0d );
    }
}
