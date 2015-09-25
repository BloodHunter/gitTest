package com.wbl.test.RTTI.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Simple_love on 2015/9/25.
 */
class DynamicProxyHandler implements InvocationHandler{
        private Object proxied;
        public DynamicProxyHandler(Object proxied){
                this.proxied = proxied;
        }
        public Object invoke(Object proxy, Method method,Object[] args) throws InvocationTargetException, IllegalAccessException {
                System.out.println("***** proxy: " + proxy.getClass() + ", method: " + method + ",args: " + args);
                if (args != null)
                        for (Object arg: args)
                                System.out.println(" " + arg);
                return method.invoke(proxied,args);
        }
}
public class DynamicProxy {
        public static void consumer(Interface iface){
                iface.doSomething();
                iface.somethingElse("dddd");
        }
        public static void main(String[] args){
                RealObject real = new RealObject();
                consumer(real);

                Interface proxy = (Interface) Proxy.newProxyInstance(
                        Interface.class.getClassLoader(),
                        new Class[]{Interface.class},
                        new DynamicProxyHandler(real)
                );
                consumer(proxy);
        }
}
