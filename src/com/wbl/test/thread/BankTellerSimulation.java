package com.wbl.test.thread;

import java.io.IOException;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created with Simple_love
 * Date: 2016/3/24.
 * Time: 9:53
 */
public class BankTellerSimulation {
        static final int MAX_LINE_SIZE = 50;
        static final int ADJUSTMENT_PERIOD = 100;
        public static void main(String[] args) throws IOException {
                ExecutorService exex = Executors.newCachedThreadPool();
                CustomerLine customers = new CustomerLine(MAX_LINE_SIZE);
                exex.execute(new CustomerGenerator(customers));
                exex.execute(new TellerManager(exex,customers,ADJUSTMENT_PERIOD));
                System.in.read();
                exex.shutdownNow();
        }
}

class Customer {
        private final int serviceTime;
        public Customer(int tm){
                serviceTime = tm;
        }
        public int getServiceTime(){
                return serviceTime;
        }
        public String toString(){
                return "[" + serviceTime +"]";
        }
}

class CustomerLine extends ArrayBlockingQueue<Customer>{
        public CustomerLine(int maxLineSize){
                super(maxLineSize);
        }
        public String toString(){
                if (this.size() == 0)
                        return "[Empty]";
                StringBuilder builder = new StringBuilder();
                for (Customer customer:this)
                        builder.append(customer);
                return builder.toString();
        }
}

class CustomerGenerator implements Runnable{
        private CustomerLine customers;
        private Random rand = new Random(47);
        public CustomerGenerator(CustomerLine cs){
                customers = cs;
        }

        @Override
        public void run() {
               try {
                       while (!Thread.interrupted()){
                               TimeUnit.MILLISECONDS.sleep(rand.nextInt(300));
                               customers.add(new Customer(rand.nextInt(1000)));
                       }
               }catch (InterruptedException e){
                       System.out.println("CustomerGenerator interrupt");
               }
                System.out.println("CustomerGenerator terminating");
        }
}

class Teller implements Runnable,Comparable<Teller>{
        private static int counter = 0;
        private final int id = counter++;
        private int customersServed = 0;
        private boolean servingCustomerLine = true;
        private CustomerLine customers;
        public Teller(CustomerLine cs){
                customers = cs;
        }

        @Override
        public void run() {
                try {
                        while (!Thread.interrupted()){
                                Customer customer = customers.take();
                                TimeUnit.MILLISECONDS.sleep(customer.getServiceTime());
                                synchronized (this){
                                        customersServed++;
                                        while (!servingCustomerLine)
                                                wait();
                                }
                        }
                }catch (InterruptedException e){
                        System.out.println(this + "interrupted");
                }
                System.out.println(this + "terminating");
        }

        public synchronized int compareTo(Teller other){
                return customersServed < other.customersServed ? -1:
                        (customersServed == other.customersServed ? 0 :1);
        }

        public synchronized void doSomethingElse(){
                servingCustomerLine = false;
                customersServed = 0;
        }

        public synchronized void serveCustomerLine(){
                assert !servingCustomerLine:"already serving: " + this;
                servingCustomerLine = true;
                notifyAll();
        }

        public String toString(){
                return "Teller " + id + " ";
        }

        public String shortString(){
                return "T " + id + " ";
        }
}

class TellerManager implements Runnable{
        private ExecutorService exec;
        private CustomerLine customers;
        private PriorityQueue<Teller> workingTellers = new PriorityQueue<>();
        private Queue<Teller> tellersDoingOtherThing = new LinkedList<>();
        private int adjustmentPeriod;
        private static Random rand = new Random(47);
        public TellerManager(ExecutorService e,CustomerLine customers,int adjustmentPeriod){
                exec = e;
                this.customers = customers;
                this.adjustmentPeriod = adjustmentPeriod;
                Teller teller = new Teller(customers);
                exec.execute(teller);
                workingTellers.add(teller);
        }

        public void adjustTellerNumber(){
                if (customers.size() /workingTellers.size() > 2){
                        if (tellersDoingOtherThing.size() > 0){
                                Teller teller = tellersDoingOtherThing.remove();
                                teller.serveCustomerLine();
                                workingTellers.offer(teller);
                                return;
                        }
                        Teller teller = new Teller(customers);
                        teller.serveCustomerLine();
                        workingTellers.offer(teller);
                }
                if (workingTellers.size() > 1 && customers.size() /workingTellers.size() < 2)
                        reassignOneTeller();
        }

        private void reassignOneTeller(){
                Teller teller = workingTellers.poll();
                teller.doSomethingElse();
                tellersDoingOtherThing.offer(teller);
        }

        @Override
        public void run() {
                try{
                        while (!Thread.interrupted()){
                                TimeUnit.MILLISECONDS.sleep(adjustmentPeriod);
                                adjustTellerNumber();
                                System.out.print(customers + "{");
                                for (Teller teller : workingTellers){
                                        System.out.print(teller.shortString() + " ");
                                }
                                System.out.println("}");
                        }
                }catch (InterruptedException e){
                        System.out.println(this + "interrupt");
                }
                System.out.println(this + "terminating");
        }

        @Override
        public String toString() {
                return "TellerManager ";
        }
}