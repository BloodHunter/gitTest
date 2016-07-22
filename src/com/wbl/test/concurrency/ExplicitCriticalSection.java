package com.wbl.test.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Simple_love on 2015/10/22.
 */
class ExplicitPariManager1 extends PairManager{
        private Lock  lock = new ReentrantLock();
        public synchronized void increment(){
                lock.lock();
                try {
                        p.incrementX();
                        p.incrementY();
                        store(getPair());
                }finally {
                        lock.unlock();
                }
        }
}

class ExplicitPariManager2 extends PairManager{
        private Lock lock = new ReentrantLock();
        public  void increment(){
                Pair temp = null;
                lock.lock();
                try {
                        p.incrementX();
                        p.incrementY();
                        temp = getPair();
                }finally {
                        lock.unlock();
                }
                store(temp);
        }
}
public class ExplicitCriticalSection {
        public static void main(String[] args){
                PairManager
                        pman1 = new ExplicitPariManager1(),
                        pman2 = new ExplicitPariManager2();
                CriticalSection.testApproaches(pman1, pman2);
        }
}
