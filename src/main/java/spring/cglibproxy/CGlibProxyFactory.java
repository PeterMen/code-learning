package spring.cglibproxy;

import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;


/**
 * 提供切面织入能力，返回被代理的对象，增强类
 *
 * */
public class CGlibProxyFactory {

    public static Object getProxyObject(Class cls, Callback callback){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(cls);
        enhancer.setCallback(callback);

        return enhancer.create();
    }
}
