package spring.jdkproxy;

import java.lang.reflect.Proxy;

public class JdkProxyFactory {

    public static Object getProxy(Object target) {// target是被代理的对象

        return   Proxy.newProxyInstance(
                target.getClass().getClassLoader(),  // 类加载器
                target.getClass().getInterfaces(),// 被代理的接口
                new UserLoginInvocationHandler(target));  // 代理对象，持有被代理对象的引用
    }
}
