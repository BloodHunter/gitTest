package com.wbl.test.generics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created withSimple_love
 * Date: 2016/3/9.
 * Time: 10:09
 */
public class Generators {

        public <T>T fill(Collection<T> col,int n,T t){
                System.out.println("col " + n);
                return t;
        }
        public <T> Set<T> fill(Set<T> set,int n){
                System.out.println(n);
                return set;
        }

        /*public <T>List<T> fill(List<T> list,int n){
                System.out.println("list"+ n);
                return list;
        }*/

        public static void main(String[] args) {
                Generators test = new Generators();
                int n = 2;
        }
}
