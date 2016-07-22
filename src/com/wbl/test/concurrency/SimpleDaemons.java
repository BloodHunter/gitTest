package com.wbl.test.concurrency;

import java.util.concurrent.TimeUnit;

/**
 * Created by Simple_love on 2015/10/21.
 */
public class SimpleDaemons implements Runnable {
        public void run(){
                try {
                        while (true){
                                TimeUnit.MILLISECONDS.sleep(100);
                                System.out.println(Thread.currentThread() + " " + this);
                        }
                }catch (InterruptedException e){
                        System.out.println("sleep() interrupted");
                }
        }

        public static void main(String[] args) throws Exception{
                for (int i = 0; i < 10; i++){
                        Thread deamod = new Thread(new SimpleDaemons());
                        deamod.setDaemon(true);
                        deamod.start();
                }
                System.out.println("All deamod Start");
                TimeUnit.MILLISECONDS.sleep(200);
        }
}
