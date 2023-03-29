package com.erich.exam.util;

import java.util.*;

public class PracticeMain {
    public static void main(String[] args){
        Set<String> nombres = new HashSet<>(Arrays.asList("Erich","Ivan","Jose","Ricardo","Ivan"));
        List<String> a = new ArrayList<>(nombres);
        Collections.shuffle(a);

        a.forEach(x -> System.out.println(x));

    }
}
