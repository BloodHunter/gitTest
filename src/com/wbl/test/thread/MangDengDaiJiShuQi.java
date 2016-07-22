package com.wbl.test.thread;

/**
 * Created with Simple_love
 * Date: 2016/5/16.
 * Time: 9:13
 */
public class MangDengDaiJiShuQi {
        public static void main(String[] args) throws InterruptedException {
                PrintContext cxt = new PrintContext();
                Printer p1 = new Printer(cxt,"Print 0",0);
                Printer p2 = new Printer(cxt,"Print 1",1);
                Printer p3 = new Printer(cxt,"Print 2",2);

                Thread t1 = new Thread(p1);
                Thread t2 = new Thread(p2);
                Thread t3 = new Thread(p3);

                t1.start();
                t2.start();
                t3.start();

                t1.join();
                t2.join();
                t3.join();
        }
}
class PrintContext{
        public volatile int cur = 1;
}
class Printer implements Runnable{

        private PrintContext cxt;
        private String name;
        private int mod;
        public Printer(PrintContext cxt,String name,int mod){
                this.cxt = cxt;
                this.name = name;
                this.mod = mod;
        }
        @Override
        public void run() {
               while (true){
                       int current = this.cxt.cur;
                       if (current > 100)
                               break;
                       if (current % 3 == mod){
                               System.out.format("[%s ] %d\n",name,current);
                               int newNum = current + 1;
                               cxt.cur = newNum;
                       }
               }
        }
}
