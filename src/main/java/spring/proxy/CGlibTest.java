package spring.proxy;

public class CGlibTest {

    public static void main(String[] args) {
        // 对需要增强的类进行增强，返回增强后的对象，这个就是spring对切面配置的具体解析并实现
        // spring 分两步，一、生成代理对象，new UserInterceptor
        // 二、 织入到目标对象，  getProxyObject
        UserLogin o = (UserLogin)CGlibProxyFactory.getProxyObject(UserLogin.class, new UserInterceptor());
        o.login();
    }
}
