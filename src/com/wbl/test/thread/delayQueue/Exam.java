package com.wbl.test.thread.delayQueue;

import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Created with Simple_love
 * Date: 2016/3/19.
 * Time: 14:29
 */
public class Exam {
        public static void main(String[] args) throws InterruptedException {
                int count = 20;
                DelayQueue<Student> students = new DelayQueue<>();
                Random random = new Random();
                CountDownLatch countDownLatch = new CountDownLatch(count+1);
                for (int i = 1; i <= 20; i++)
                        students.add(new Student("student" + i,30+random.nextInt(120),countDownLatch));
                Thread teacherThread = new Thread(new Teacher(students));
                students.add(new EndExam(students, 120, countDownLatch, teacherThread));
                teacherThread.start();
                countDownLatch.await();
                System.out.println(" 考试时间到，全部交卷！");
                double d = -0.5;
                System.out.println(Math.ceil(d));
                System.out.println(Math.floor(d));
        }
}
class Student implements Runnable,Delayed{
        private String name;
        private long workTime; //考试时间
        private long submitTime; //交卷时间
        private boolean isForce = false;
        private CountDownLatch countDownLatch;

        public Student() {
        }

        public Student(String name, long workTime,CountDownLatch countDownLatch) {
                this.name = name;
                this.workTime = workTime;
                this.submitTime = TimeUnit.NANOSECONDS.convert(workTime,TimeUnit.NANOSECONDS) + System.nanoTime();
                this.countDownLatch = countDownLatch;
        }

        @Override
        public long getDelay(TimeUnit unit) {
                return unit.convert(submitTime-System.nanoTime(),TimeUnit.NANOSECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
                if (o == null || ! (o instanceof Student))
                        return 1;
                if (o == this)
                        return 0;
                Student temp = (Student)o;
                if (this.workTime > temp.workTime)
                        return 1;
                else if (this.workTime == temp.workTime)
                        return 0;
                else
                        return -1;
        }

        @Override
        public void run() {
                if (isForce) {
                        System.out.println(name + " 交卷, 希望用时" + workTime + "分钟"+" ,实际用时 120分钟" );
                }else {
                        System.out.println(name + " 交卷, 希望用时" + workTime + "分钟"+" ,实际用时 "+workTime +" 分钟");
                }
                countDownLatch.countDown();
        }

        public boolean isForce() {
                return isForce;
        }

        public void setForce(boolean isForce) {
                this.isForce = isForce;
        }
}

class Teacher implements Runnable{

        private DelayQueue<Student> students;

        public Teacher(DelayQueue<Student> students) {
                this.students = students;
        }

        @Override
        public void run() {
                try {
                        System.out.println("exam start");
                        while (!Thread.interrupted())
                                students.take().run();
                }catch (InterruptedException e){
                        System.out.println("exam end");
                }
        }
}

class EndExam extends Student{
        private DelayQueue<Student> students;
        private CountDownLatch countDownLatch;
        private Thread teacher;

        public EndExam(DelayQueue<Student> students,long workTime,CountDownLatch countDownLatch,Thread teacher){
                super("强制收卷",workTime,countDownLatch);
                this.students = students;
                this.countDownLatch = countDownLatch;
                this.teacher = teacher;
        }

        @Override
        public void run() {
                teacher.interrupt();
                System.out.println("强制收卷");
                Student temp;
                for (Iterator<Student> iterator = students.iterator();iterator.hasNext();){
                        temp = iterator.next();
                        temp.setForce(true);
                        temp.run();
                }
                countDownLatch.countDown();
        }
}
