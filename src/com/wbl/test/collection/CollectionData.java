package com.wbl.test.collection;

import com.wbl.test.array.Generator;

import java.util.ArrayList;

/**
 * Created by Simple_love on 2015/10/11.
 */
public class CollectionData<T> extends ArrayList<T> {
        public CollectionData(Generator<T> generator,int quantity){
                for (int i = 0; i < quantity; i++){
                        add(generator.next());
                }
        }
        public static <T> CollectionData<T> list(Generator<T> generator,int quantity){
                return new CollectionData<>(generator,quantity);
        }
}
