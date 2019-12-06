package org.example.smallestpositiveint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class SmallestPositiveIntegerOrrot {

    /*
    Write a function: def solution(A)
    that, given an array A of N integers returns the smallest positive integer (greater than 0) that does
    not occur in A.
    For example, Given A (1, 3. 6, 4, 2). the function should return S.
    Given A ( 1. 2, 3), the function should return 4.
    Given A • the f should return 1 .
    Write an efficient algorithm for the following N is an integer within the range (1..100,
    each element of array A is an integer within the range (-100.000 to 100.000l.
     */
    public static Integer findSmallestPositiveInteger(Integer[] integers) {

        List<Integer> numbers = getSortedDistinctIntegers(integers);

        if (numbers.size() == 1) {
            return findNextPositiveValue(numbers.get(0));
        }

        for (Integer i = 1; i < numbers.size(); i++) {

            // if the array has more than one
            Integer before = numbers.get(i - 1);
            Integer current = numbers.get(i);

            if (before + 1 != current) {
                return findNextPositiveValue(before);
            }
        }
        return findNextPositiveValue(numbers.get(numbers.size() - 1));
    }

    private static List<Integer> getSortedDistinctIntegers(Integer[] integers) {

        return Arrays.stream(integers)
                .filter(SmallestPositiveIntegerOrrot::isPositive)
                .distinct()
                .sorted()
                .collect(Collectors.toCollection(SmallestPositiveIntegerOrrot::buildListWithZero));
    }

    private static boolean isPositive(Integer value) {
        return value > 0;
    }

    private static List<Integer> buildListWithZero() {
        // Setting an initial capacity will be better!
        List<Integer> initialList = new ArrayList<>();
        initialList.add(0);
        return initialList;
    }

    private static Integer findNextPositiveValue(Integer number) {
        if (number + 1 > 0) {
            return number + 1;
        }
        return 1;
    }
}