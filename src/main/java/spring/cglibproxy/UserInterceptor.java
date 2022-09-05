package spring.cglibproxy;


import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;



/**
 * 增强对象，谁需要增强，就把它织入到谁的切面上
 * */
public class UserInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {


        System.out.println("--登录前----");

        methodProxy.invokeSuper(o, objects);

        System.out.println("--登录后----");
        return null;
    }
}
