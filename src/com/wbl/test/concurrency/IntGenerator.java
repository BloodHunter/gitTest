package com.wbl.test.concurrency;

/**
 * Created by Simple_love on 2015/10/21.
 */
public abstract class  IntGenerator {
        private volatile boolean canceled = false;
        public abstract  int next();
        public void cancel(){
                canceled = true;
        }
        public boolean isCanceled(){
                return canceled;
        }
}
