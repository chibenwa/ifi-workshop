package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimaps;

public class Cata3
{
    public static class People {
        String name;
        float income;
        int age;
        String gender;

        public People(String name, float income, int age, String gender) {
            this.name = name;
            this.income = income;
            this.age = age;
            this.gender = gender;
        }
    }

    public Map<String, Double> incomeByBender(List<People> input) {
        return input.stream()
            .collect(ImmutableListMultimap.toImmutableListMultimap(p -> p.gender, p -> p.income))
            .asMap()
            .entrySet()
            .stream()
            .collect(ImmutableMap.toImmutableMap(e -> e.getKey(),
                e -> e.getValue().stream()
                    .mapToDouble(income -> income)
                    .average()
                    .getAsDouble()));
    }

    @Test
    public void exercice1() {
        assertEquals(incomeByBender(List.of(
            new People("Leopold", 1000, 21, "Male"),
            new People("Cedric", 800, 31, "Male"),
            new People("Alphonse", 2000, 25, "Female"),
            new People("Louise", 500, 18, "Female"),
            new People("Edouard", 3000, 45, "ND"),
            new People("Edgard", 459, 25, "Male"),
            new People("Mathilde", 1000, 34, "Female"))
        ), ImmutableMap.of(
            "Male", 753.0d,
            "Female", 1166.6666666666667d,
            "ND", 3000.0d
        ));
    }

    /*
       return input.stream()
            .collect(ImmutableListMultimap.toImmutableListMultimap(p -> p.gender, p -> p.income))
            .asMap()
            .entrySet()
            .stream()
            .collect(ImmutableMap.toImmutableMap(e -> e.getKey(),
                e -> e.getValue().stream()
                    .mapToDouble(income -> income)
                    .average()
                    .getAsDouble()));
     */
}
