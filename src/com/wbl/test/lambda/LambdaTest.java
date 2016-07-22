package com.wbl.test.lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created with Simple_love
 * Date: 2016/5/25.
 * Time: 10:28
 */
public class LambdaTest {
        public static void main(String[] args) {
                List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
                primes.stream().map((x)->x*2).forEach(System.out::println);
                System.out.println(primes.stream().map(String::valueOf).collect(Collectors.joining(",")));
        }
}
