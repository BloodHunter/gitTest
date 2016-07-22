package com.wbl.test.collection;

import java.util.*;

/**
 * Created by Simple_love on 2015/10/11.
 */
public class FlyweightMap extends AbstractMap<String,String> {
        public static final String[][] DATA = {{"aaa","bbb"},{"adaa","bdbb"},{"aasa","bfbb"},
                {"aafa","bbtb"},{"aasa","bhbb"},{"aaha","bbdb"}};

        private static class Entry implements Map.Entry<String,String>{
                int index;
                Entry(int index){
                        this.index = index;
                }
                public boolean equals(Object o){
                        return DATA[index][0].equals(o);
                }
                public String getKey(){
                        return DATA[index][0];
                }
                public String getValue(){
                        return DATA[index][1];
                }
                public String setValue(String value){
                        throw new UnsupportedOperationException();
                }
                public int hashCode(){
                        return 11;
                }
        }
        static class EntrySet extends AbstractSet<Map.Entry<String,String>>{
                private int size;
                EntrySet(int size){
                       if (size < 0){
                               this.size = 0;
                       }else if (size > DATA.length){
                               this.size = DATA.length;
                       }else {
                               this.size = size;
                       }
                }
                public int size(){
                        return size;
                }
                private class Iter implements Iterator<Map.Entry<String,String>>{
                        private Entry entry = new Entry(-1);
                        public boolean hasNext(){
                                return entry.index < size - 1;
                        }
                        public Map.Entry<String,String> next(){
                                entry.index ++;
                                return entry;
                        }
                        public void remove(){
                                throw new UnsupportedOperationException();
                        }
                }
                public Iterator<Map.Entry<String,String>> iterator(){
                        return new Iter();
                }
        }

        private static Set<Map.Entry<String,String>> entries = new EntrySet(DATA.length);
        @Override
        public Set<Map.Entry<String, String>> entrySet() {
                return entries;
        }
}
