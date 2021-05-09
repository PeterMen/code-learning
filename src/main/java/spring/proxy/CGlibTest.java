package spring.proxy;

public class CGlibTest {

    public static void main(String[] args) {
        // 对需要增强的类进行增强，返回增强后的对象，这个就是spring对切面配置的具体解析并实现
        UserLogin o = (UserLogin)CGlibProxyFactory.getProxyObject(UserLogin.class, new UserInterceptor());
        o.login();
    }
}
