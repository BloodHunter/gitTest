package com.wbl.test.collection;

import java.util.LinkedHashMap;

/**
 * Created by Simple_love on 2015/10/13.
 */
public class LinkedHashMapDemo {
        public static void main(String[] args){
                LinkedHashMap<Integer,String> linkMap = new LinkedHashMap<>();
                linkMap.put(1,"A1");
                linkMap.put(1,"b1");
                linkMap.put(3,"c1");
                linkMap.put(4,"d1");
                linkMap.put(5, "e1");
                System.out.println(linkMap);
                linkMap = new LinkedHashMap<>(16,0.75f,true);
                linkMap.put(0,"A1");
                linkMap.put(1,"b1");
                linkMap.put(2,"c1");
                linkMap.put(3, "d1");
                linkMap.put(4,"e1");
                for (int i = 0; i < 3; i++){
                        linkMap.get(i);
                }
                System.out.println(linkMap);
                linkMap.get(0);
                System.out.println(linkMap);
        }
}
