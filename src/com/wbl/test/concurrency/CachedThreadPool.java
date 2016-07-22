package com.wbl.test.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Simple_love on 2015/10/20.
 */
public class CachedThreadPool {
        public static void main(String[] args){
                ExecutorService exec = Executors.newCachedThreadPool();
                for (int i =0; i < 5; i ++)
                        exec.execute(new LiftOff());
                exec.shutdown();
        }
}
