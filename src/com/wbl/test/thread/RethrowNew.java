package com.wbl.test.thread;

/**
 * Created withSimple_love
 * Date: 2016/3/7.
 * Time: 14:44
 */

class OneException extends Exception{
        public OneException(String s){
                super(s);
        }
}

class TwoException extends Exception{
        public TwoException(String s){
                super(s);
        }
}
public class RethrowNew {
        public static void  f() throws OneException{
                System.out.println("origination in f()");
                throw new OneException("throw from f()");
        }

        public static void main(String[] args) {
                try {
                        try {
                                f();
                        }catch (OneException e){
                                System.out.println("caught in inner try");
                                e.printStackTrace();
                                throw new TwoException("from inner try");
                        }
                }catch (TwoException e){
                        System.out.println("caught outer try");
                        e.printStackTrace(System.out);
                }
        }
}
