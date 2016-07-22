package com.wbl.test.array;

/**
 * Created by Simple_love on 2015/10/10.
 */
public class CountingGenerator {
        public static class Boolean implements Generator<java.lang.Boolean>{
                private boolean value = false;
                public java.lang.Boolean next(){
                        value = !value;
                        return value;
                }
        }

        public static class Byte implements Generator<java.lang.Byte>{
                private byte value =0;
                public java.lang.Byte next(){
                        return value++;
                }
        }

        static char[] chars = ("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
        public static class Character implements Generator<java.lang.Character>{
                int index = -1;
                public java.lang.Character next(){
                        index = (index + 1) % chars.length;
                        return chars[index];
                }
        }

        public static class String implements Generator<java.lang.String>{
                private int length = 7;
                Generator<java.lang.Character> cg = new Character();
                public String(){}
                public String(int length){
                        this.length = length;
                }
                public java.lang.String next(){
                        char[] buf = new char[length];
                        for (int i = 0; i < length; i++){
                                buf[i] = cg.next();
                        }
                        return new java.lang.String(buf);
                }
        }

        public static class Integer implements Generator<java.lang.Integer>{
                private int value = 0;
                public java.lang.Integer next(){
                        return value++;
                }
        }

        public static class Double implements Generator<java.lang.Double>{
                private double value = 0.0;
                public java.lang.Double next(){
                        return value+= 1.0;
                }
        }
}
