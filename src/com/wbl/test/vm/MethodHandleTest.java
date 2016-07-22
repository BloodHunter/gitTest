package com.wbl.test.vm;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;

/**
 * Created by Simple_love on 2015/11/12.
 */
public class MethodHandleTest  {
        static class ClassA{
                public void println(String s){
                        System.out.println(s);
                }
        }

        public static void main(String[] args) throws Throwable{
                Object obj = System.currentTimeMillis() % 2 == 0 ? System.out : new ClassA();
                getPrintlnMH(obj).invokeExact("dddd");
        }

        private static MethodHandle getPrintlnMH(Object receiver) throws Throwable{
                MethodType mt = MethodType.methodType(void.class,String.class);

                return MethodHandles.lookup().findVirtual(receiver.getClass(),"println",mt).bindTo(receiver);
        }
}
