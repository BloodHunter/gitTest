package com.wbl.test.concurrency;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Simple_love on 2015/10/22.
 */
class Accessor implements Runnable{
        private final int id;
        public Accessor(int id){
                this.id = id;
        }
        public void run(){
                while (! Thread.currentThread().isInterrupted()){
                        ThreadLocalVariableHolder.increment();
                        System.out.println(this);
                        Thread.yield();
                }
        }

        public String toString(){
                return "#" + id +": " + ThreadLocalVariableHolder.get();
        }
}
public class ThreadLocalVariableHolder {
        private static ThreadLocal<Integer> value = new ThreadLocal<Integer>(){
                private Random rand = new Random(47);
                protected synchronized Integer initialValue(){
                        return rand.nextInt();
                }
        };

        public static void increment(){
                value.set(value.get() + 1);
        }

        public static int get(){
                return value.get();
        }

        public static void main(String[] args) throws Exception{
                ExecutorService exec = Executors.newCachedThreadPool();
                for (int i = 0; i < 5; i++){
                        exec.execute(new Accessor(i));
                }
                TimeUnit.SECONDS.sleep(1);
                exec.shutdownNow();
        }
}
