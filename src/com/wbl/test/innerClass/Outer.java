package com.wbl.test.innerClass;

/**
 * Created withSimple_love
 * Date: 2016/3/1.
 * Time: 8:50
 */
public class Outer {
        private int n;
        class Inner{
                public void fun(){
                        System.out.println(n);
                }
        }

        public static void main(String[] args) {
                Outer outer = new Outer();
                Outer.Inner inner = outer.new Inner();
                inner.fun();
        }
}
