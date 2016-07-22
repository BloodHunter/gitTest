package com.wbl.test.Enum;

import java.util.Random;

/**
 * Created by Simple_love on 2015/10/20.
 */
public class RoShamBo {
        public static <T extends Competitor<T>> void match(T a, T b){
                System.out.println(a.toString() + " VS " + b.toString() + ": " + a.compete(b));
        }

        public static <T extends Enum<T> & Competitor<T>> void play(Class<T> rsbClass,int size){
                for (int i = 0; i < size; i++){
                        match(Enums.random(rsbClass), Enums.random(rsbClass));
                }
        }
}
class Enums{
        private static Random rand = new Random(47);
        public static <T extends Enum<T>> T  random(Class<T> superClass){
                return random(superClass.getEnumConstants());
        }
        public static <T> T random(T[] values){
                return values[rand.nextInt(values.length)];
        }
}
