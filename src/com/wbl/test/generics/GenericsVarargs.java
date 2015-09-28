package com.wbl.test.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Simple_love on 2015/9/28.
 */
public class GenericsVarargs {
        public static <T> List<T> makeList(T... args){
                List<T> result = new ArrayList<>();
                for (T item: args){
                        result.add(item);
                }
                return result;
        }
        public static void main(String[] args){
                List<String> ls = makeList("A");
                System.out.println(ls);
                ls = makeList("A","B","C");
                System.out.println(ls);
                String[] str = "ADFSFHVKEOGLEOG".split("");
                ls = makeList(str);
                System.out.println(ls);
        }
}
