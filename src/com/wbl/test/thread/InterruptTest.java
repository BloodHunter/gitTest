package com.wbl.test.thread;

/**
 * Created with Simple_love
 * Date: 2016/3/18.
 * Time: 14:15
 */
public class InterruptTest {
        public static void main(String[] args) throws InterruptedException {
                Thread t = new Thread(new SleepInterrupt());
                t.start();
                Thread.sleep(100);
                t.interrupt();
        }
}

class SleepInterrupt implements Runnable{

        @Override
        public void run() {
                while (true){
                        System.out.println("Thread " + Thread.currentThread().getName() + " is run");
                        try {
                                System.out.println("try " +Thread.currentThread().isInterrupted());
                                Thread.sleep(100);
                        } catch (InterruptedException e) {
                                e.printStackTrace();
                                System.out.println("catch " +Thread.currentThread().isInterrupted());
                                return;
                        }
                }
        }
}
