package com.wbl.test.interfaces;

/**
 * Created withSimple_love
 * Date: 2016/2/29.
 * Time: 17:02
 */
public abstract class Instrument {
        Instrument(){
                print();
        }
        public abstract void print();
        public  void fun(){
                System.out.println("super function");
        }

        public static void main(String[] args) {
                Wind wind = new Wind();
                wind.print();
                wind.fun();
        }
}
class Wind extends Instrument{
        private int i = 1;
        @Override
        public void print() {
                System.out.println("i = " + i);
        }

        @Override
        public void fun() {
                System.out.println("son function");
        }
}
