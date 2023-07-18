package com.leadboot.gate.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 使用 MyDynamicProxy 类为接口创建动态代理
 */
public interface MyInterface {
    void doSomething();

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
