package com.wbl.test.generics;

import java.util.ArrayList;

/**
 * Created withSimple_love
 * Date: 2016/3/9.
 * Time: 10:21
 */
public class BasicGenerator<T> {
        private Class<T> type;
        public BasicGenerator(Class<T> type){
                this.type = type;
        }

        public T next(){
                try {
                        return type.newInstance();
                }catch (Exception e){
                       throw new RuntimeException(e);
                }
        }

        public static <T> BasicGenerator<T> create(Class<T> type){
                return new BasicGenerator<>(type);
        }

        public static void main(String[] args) {
                Class c1 = new ArrayList<String>().getClass();
                Class c2 = new ArrayList<Integer>().getClass();
                System.out.println(c1==c2 );
        }
}
