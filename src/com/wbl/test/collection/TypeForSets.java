package com.wbl.test.collection;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Simple_love on 2015/10/12.
 */
class SetType{
        int i;
        public SetType(int n){
               this.i = n;
        }
        public boolean equals(Object o){
                return o instanceof SetType && (i == ((SetType)o).i);
        }
        public String toString(){
                return Integer.toString(i);
        }
}
class HashType extends SetType{
        public HashType(int n){super(n);}
        public int hashCode(){return i;}
}

class TreeType extends SetType implements Comparable<TreeType>{
        public TreeType(int n){super(n);}
        public int compareTo(TreeType arg){
                return (arg.i < i ? -1 : (arg.i == i ? 0: 1));
        }
}
public class TypeForSets {
       static <T> Set<T> fill(Set<T> set, Class<T> type){
               try {
                       for (int i = 0; i < 10; i++)
                               set.add(type.getConstructor(int.class).newInstance(i));
               }catch (Exception e){
                       throw new RuntimeException();
               }
               return set;
       }
        static <T> void test(Set<T> set, Class<T> type){
                fill(set,type);
                fill(set,type);
                fill(set,type);
                System.out.println(set);
        }

        public static void main(String[] args){
                test(new HashSet<HashType>(),HashType.class);
                test(new LinkedHashSet<HashType>(),HashType.class);
                test(new TreeSet<TreeType>(),TreeType.class);

                test(new HashSet<SetType>(),SetType.class);
                test(new HashSet<TreeType>(),TreeType.class);
                test(new LinkedHashSet<SetType>(),SetType.class);
                test(new LinkedHashSet<TreeType>(),TreeType.class);
        }
}
