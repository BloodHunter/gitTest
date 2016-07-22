package com.wbl.test.thread.countDownLatch;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * Created with Simple_love
 * Date: 2016/3/19.
 * Time: 16:06
 */
public class CountDownLatchDemo {
        final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        public static void main(String[] args) throws InterruptedException {
                CountDownLatch countDownLatch = new CountDownLatch(2);    //两个工人协同工作
                Worker worker1 = new Worker("work1",5000,countDownLatch);
                Worker worker2 = new Worker("work2",8000,countDownLatch);
                worker1.start();
                worker2.start();
                countDownLatch.await();   //等待所有的工人结束
                System.out.println("all work done at " + sdf.format(new Date()));
        }

        static class Worker extends Thread{
                String name;
                int workTime;
                CountDownLatch countDownLatch;
                public Worker(String name,int workTime,CountDownLatch countDownLatch){
                        this.name = name;
                        this.workTime = workTime;
                        this.countDownLatch = countDownLatch;
                }

                public void doWork(){
                        try {
                                Thread.sleep(workTime);
                        }catch (InterruptedException e){
                                e.printStackTrace();
                        }
                }

                @Override
                public void run() {
                        System.out.println("Worker " + name + " do work begin at " + sdf.format(new Date()));
                        doWork();//工作了
                       System.out.println("Worker "+name+" do work complete at "+sdf.format(new Date()));
                       countDownLatch.countDown();//工人完成工作，计数器减一

                }
        }
}
