package ru.job4j.assertj;


import java.util.stream.Stream;

public class BoxApp {
    public static void main(String[] args) {




      //  Iterator<Integer> it  = new EvenNumbersIterator(new int[] {4, 2, 1, 1});



   /*     System.out.println(it.next());
        System.out.println(it.next());
        System.out.println(it.next());*/

        Stream.of("dd2", "aa2", "bb1", "bb3", "cc4")
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("A");
                })
                .forEach(s -> System.out.println("forEach: " + s));








    }
}
