package com.wbl.test.collection;

/**
 * Created by Simple_love on 2015/10/13.
 */
public abstract class Test<C> {
        String name;
        public Test(String name){
                this.name = name;
        }
        abstract int test(C container, TestParam tp);
}
