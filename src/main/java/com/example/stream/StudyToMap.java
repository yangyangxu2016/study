package com.example.stream;

import javax.xml.stream.XMLOutputFactory;
import java.util.Arrays;
import java.util.List;
import java.util.TooManyListenersException;
import java.util.stream.Collectors;

/**
 * @author xuyangyang
 */
public class StudyToMap {

    public static void main(String[] args) {


        List<Integer> word1 = Arrays.asList(1, 2, 3);
        List<Integer> word2 = Arrays.asList( 4, 5);

        List<int[]> collect = word1.stream().
                flatMap(x -> word2.stream().map(j -> new int[]{x, j}))
                .collect(Collectors.toList());


        collect.forEach(x-> System.out.println(x));


    }
}
