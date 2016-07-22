package com.wbl.test.thread;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created with Simple_love
 * Date: 2016/3/19.
 * Time: 12:25
 */
public class NotifyVsBNotifyAll {
        public static void main(String[] args) throws InterruptedException {
                ExecutorService exec = Executors.newCachedThreadPool();
                for (int i = 0; i < 5; i++){
                        exec.execute(new Task());
                }
                exec.execute(new Task2());
                Timer timer = new Timer();
                timer.scheduleAtFixedRate(new TimerTask() {
                        boolean prod = true;
                        @Override
                        public void run() {
                                if (prod){
                                        System.out.print("\nnotify()");
                                        Task.blocker.prod();
                                        prod = false;
                                }else {
                                        System.out.print("\nnotifyAll()");
                                        Task.blocker.prodAll();
                                        prod = true;
                                }
                        }
                },400,400);

                TimeUnit.SECONDS.sleep(5);
                timer.cancel();
                System.out.println("\nTimer canceled");
                TimeUnit.MILLISECONDS.sleep(500);
                System.out.println("Task2.blocker.prodAll()");
                Task2.blocker.prodAll();
                TimeUnit.MILLISECONDS.sleep(500);
                System.out.println("\nShutting down");
                exec.shutdownNow();
        }
}
class Blocker{
        synchronized void waitingCall(){
                try {
                        while (!Thread.interrupted()){
                                wait();
                                System.out.print(Thread.currentThread()+ " ");
                        }
                }catch (InterruptedException e){

                }
        }
        synchronized void prod(){
                notify();
        }
        synchronized void prodAll(){
                notifyAll();
        }
}
class Task implements Runnable{
        static Blocker blocker = new Blocker();

        @Override
        public void run() {
                blocker.waitingCall();
        }
}

class Task2 implements Runnable{
        static Blocker blocker = new Blocker();

        @Override
        public void run() {
                blocker.waitingCall();
        }
}