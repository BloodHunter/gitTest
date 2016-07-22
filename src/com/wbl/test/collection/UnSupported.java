package com.wbl.test.collection;

import java.util.*;

/**
 * Created by Simple_love on 2015/10/12.
 */
public class UnSupported {
        static void test(String msg,List<String> list){
                System.out.println("------  " + msg + "  -----" );
                Collection<String> c = list;
                Collection<String> subList = list.subList(1,8);
                Collection<String> c2 = new ArrayList<>(subList);

                try {
                        c.retainAll(c2);
                }catch (Exception e){
                        System.out.println("retainAll(): " + e);
                }

                try {
                        c.removeAll(c2);
                }catch (Exception e){
                        System.out.println("removeAll(): " + e);
                }

                try {
                        c.clear();
                }catch (Exception e){
                        System.out.println("clear(): " + e);
                }

                try {
                        c.add("X");
                }catch (Exception e){
                        System.out.println("add(): " + e);
                }

                try {
                        c.addAll(c2);
                }catch (Exception e){
                        System.out.println("addAll(): " + e);
                }

                try {
                        list.set(0,"X");
                }catch (Exception e){
                        System.out.println("List.size(): " + e);
                }
        }

        public static void main(String[] args){
                List<String> list = Arrays.asList("A B C D E F G H I J K L".split(" "));
                test("Modifiable copy",new ArrayList<String>(list));
                test("Arrays.asList()",list);
                test("unmodifiableList()",Collections.unmodifiableList(new ArrayList<String>(list)));
        }
}
