package com.wbl.test.array;

import com.wbl.test.RTTI.Manx;

import java.util.Random;

/**
 * Created by Simple_love on 2015/10/10.
 */
public class RandomGenerator {
        private static Random r = new Random(47);
        public static class Boolean implements Generator<java.lang.Boolean>{
                public java.lang.Boolean next(){
                        return r.nextBoolean();
                }
        }

        public static class Byte implements Generator<java.lang.Byte>{
                public java.lang.Byte next(){
                        return (byte)r.nextInt();
                }
        }

        public static class Character implements Generator<java.lang.Character>{
                public java.lang.Character next(){
                        return CountingGenerator.chars[r.nextInt(CountingGenerator.chars.length)];
                }
        }

        public static class String extends CountingGenerator.String{
                public String(){};
                public String(int length){
                        super(length);
                }
        }

        public static class Integer implements Generator<java.lang.Integer>{
                private int value = 0;
                public java.lang.Integer next(){
                        return value++;
                }
        }

        public static class Double implements Generator<java.lang.Double>{
                public java.lang.Double next(){
                        long trimmed = Math.round(r.nextDouble() * 100);
                        return ((double)trimmed) / 100;
                }
        }
}
