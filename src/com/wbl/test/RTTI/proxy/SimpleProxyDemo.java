package com.wbl.test.RTTI.proxy;

/**
 * Created by Simple_love on 2015/9/25.
 */
interface Interface{
        void doSomething();
        void somethingElse(String arg);
}
class RealObject implements Interface{

        @Override
        public void doSomething() {
                System.out.println("doSomething");
        }

        @Override
        public void somethingElse(String arg) {
                System.out.println("somethingElse " + arg);
        }
}
class SimpleProxy implements Interface{
        public static int count = 0;
        private Interface proxied;
        public SimpleProxy(Interface proxied){
                this.proxied = proxied;
        }

        @Override
        public void doSomething() {
                count++;
                System.out.println("SimpleProxy doSomething");
                proxied.doSomething();
        }

        @Override
        public void somethingElse(String arg) {
                System.out.println("SimpleProxy somethingElse ");
                proxied.somethingElse(arg);
        }
}
public class SimpleProxyDemo {
        public static void consumer(Interface iface){
                iface.doSomething();
                iface.somethingElse("dddd");
        }
        public static void main(String[] args){
                consumer(new RealObject());
                consumer(new SimpleProxy(new RealObject()));
                consumer(new SimpleProxy(new RealObject()));
                consumer(new SimpleProxy(new RealObject()));
                System.out.println(SimpleProxy.count);
        }
}
