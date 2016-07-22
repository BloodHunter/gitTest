package com.wbl.test.collection;

import com.wbl.test.array.Generator;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by Simple_love on 2015/10/11.
 */
public class CollectionDataTest {
        public static void main(String[] args){
                Set<String> set = new LinkedHashSet<>(new CollectionData<String>(new Goverment(),2));
                set.addAll(CollectionData.list(new Goverment(),2));
                System.out.println(set);
        }
}
class Goverment implements Generator<String>{
        String[] fundation = ("dd  dfff9ejds ;kpoi").split(" ");
        private int index;
        public String next(){
                return fundation[index++];
        }
}
