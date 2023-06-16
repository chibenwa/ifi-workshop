package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import com.google.common.collect.ImmutableMap;

public class Cata2 {
    enum Gender {
        Male,
        Female;
    }

    public static class People {
        String name;
        float income;
        int age;
        Gender gender;

        public People(String name, float income, int age, Gender gender) {
            this.name = name;
            this.income = income;
            this.age = age;
            this.gender = gender;
        }
    }

    // 1. Can you spot the issue?
    // 2. Rewrite me with the stream API
    public Optional<Double> computeAvgIncomeMale20to30(List<People> input) {
        double sum = 0;
        int count = 0;
        for (People p : input) {
            if (p.gender == Gender.Male && p.age >= 20 && p.age < 30) {
                sum += p.income;
                count++;
            }
        }
        return Optional.of(sum / count);
    }

    // 3. Rewrite functional style...
    public String biggestSalary(List<People> input) {
        String name = null;
        float income = Float.MIN_VALUE;
        for (People p : input) {
            if (p.income > income) {
                income = p.income;
                name = p.name;
            }
        }
        return name;
    }

    /*
    What's the difference with serviceWithTheOldestEmployee ?
     */
    public String oldestEmployee(Map<String, List<People>> employeeByService) {
        int age = Integer.MIN_VALUE;
        String name = null;
        for (List<People> serviceStaff : employeeByService.values()) {
            for (People p : serviceStaff) {
                if (p.age > age) {
                    age = p.age;
                    name = p.name;
                }
            }
        }
        return name;
    }

    public String serviceWithTheOldestEmployee(Map<String, List<People>> employeeByService) {
        int age = Integer.MIN_VALUE;
        String name = null;
        for (Map.Entry<String, List<People>> service : employeeByService.entrySet()) {
            for (People p : service.getValue()) {
                if (p.age > age) {
                    age = p.age;
                    name = service.getKey();
                }
            }
        }
        return name;
    }

    @Test
    public void exercice1() {
        assertEquals(computeAvgIncomeMale20to30(List.of(
            new People("Leopold", 1000, 21, Gender.Male),
            new People("Cedric", 800, 31, Gender.Male),
            new People("Alphonse", 2000, 25, Gender.Female),
            new People("Louise", 500, 18, Gender.Female),
            new People("Edouard", 3000, 45, Gender.Male),
            new People("Edgard", 459, 25, Gender.Male),
            new People("Mathilde", 1000, 34, Gender.Female))
        ), Optional.of(729.5));
    }

    @Test
    public void exercice2() {
        assertEquals(biggestSalary(List.of(
            new People("Leopold", 1000, 21, Gender.Male),
            new People("Cedric", 800, 31, Gender.Male),
            new People("Alphonse", 2000, 25, Gender.Female),
            new People("Louise", 500, 18, Gender.Female),
            new People("Edouard", 3000, 45, Gender.Male),
            new People("Edgard", 459, 25, Gender.Male),
            new People("Mathilde", 1000, 34, Gender.Female))
        ), "Edouard");
    }

    @Test
    public void exercice3() {
        assertEquals(oldestEmployee(
                ImmutableMap.of(
                    "Administration", List.of(
                        new People("Leopold", 1000, 21, Gender.Male),
                        new People("Cedric", 800, 31, Gender.Male),
                        new People("Alphonse", 2000, 25, Gender.Female)),
                    "RnD", List.of(new People("Louise", 500, 18, Gender.Female),
                        new People("Edouard", 3000, 45, Gender.Male)),
                    "Sales", List.of(new People("Edgard", 459, 25, Gender.Male),
                        new People("Mathilde", 1000, 34, Gender.Female)))),
            "Edouard");
    }

    @Test
    public void exercice4() {
        assertEquals(serviceWithTheOldestEmployee(
                ImmutableMap.of(
                    "Administration", List.of(
                        new People("Leopold", 1000, 21, Gender.Male),
                        new People("Cedric", 800, 31, Gender.Male),
                        new People("Alphonse", 2000, 25, Gender.Female)),
                    "RnD", List.of(new People("Louise", 500, 18, Gender.Female),
                        new People("Edouard", 3000, 45, Gender.Male)),
                    "Sales", List.of(new People("Edgard", 459, 25, Gender.Male),
                        new People("Mathilde", 1000, 34, Gender.Female)))),
            "RnD");
    }
}
