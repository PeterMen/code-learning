package spring.jdkproxy;


import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


/**
 * 增强对象，谁需要增强，就把它织入到谁的切面上
 * */
public class UserLoginInvocationHandler implements InvocationHandler {

    /**
     * 持有对原始对象的引用
     * */
    private Object target;

    UserLoginInvocationHandler(Object target){
        this.target = target;
    }

    /**
     * @param proxy jdk生成的代理对象proxy
     * @param method 实际调用的被代理的方法
     * @param args 实际调用的被代理方法的参数列表
     * */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {


        System.out.println("--登录前----");

        method.invoke(target, args);

        System.out.println("--登录后----");
        return null;
    }
}
