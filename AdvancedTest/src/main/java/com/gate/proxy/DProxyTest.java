package com.gate.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author: Fred
 * @date: 2023/7/18 19:35
 * @description: (类的描述)
 */
public class DProxyTest {

    public static void main(String[] args) {
        MyInterface proxy = MyDynamicProxy.createProxy(MyInterface.class, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("Before doSomething");
                // 调用原始对象的方法，如果需要
                System.out.println("After doSomething");
                return null;
            }
        });

        proxy.doSomething();
    }
}
