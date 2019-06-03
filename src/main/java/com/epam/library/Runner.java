package com.epam.library;

import java.util.*;

public class Runner {
    public static void main(String[] args) {
        Map<Integer, String> hashMap = new HashMap<>();
        hashMap.put(1, "one");
        hashMap.put(2, "two");


        hashMap.forEach((key, value) -> System.out.println(key + " : " + value));

        Map<Integer, String> hashTable = new LinkedHashMap<>();
        hashTable.put(1, "one");
        hashTable.put(2, "two");
        hashTable.put(3, "three");
        hashTable.put(4, "four");

        hashTable.forEach((k, v) -> System.out.println(k + " " + v));

        for (Map.Entry<Integer, String> entry : hashTable.entrySet()) {
            Integer key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key + " : " + value);
        }

        Set<String> strings = new LinkedHashSet<>();
        strings.add("First");
        strings.add("second");
        strings.add("third");
        strings.add("Fourth");

        strings.forEach(System.out::println);

    }


}
