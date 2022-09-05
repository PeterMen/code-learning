package spring.jdkproxy;

import lombok.SneakyThrows;
import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Proxy;

public class Test {

    @SneakyThrows
    public static void main(String[] args) {
        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        // 对需要增强的类进行增强，返回增强后的对象，这个就是spring对切面配置的具体解析并实现
        UserLoginInterface o = (UserLoginInterface)JdkProxyFactory.getProxy(new UserLogin());

        // 调用顺序： $Proxy0 -->  UserLoginInvocationHandler --> UserLogin
        // 为什么JDK代理必须基于接口？ 生成的代理类本身集成了Proxy类，如果代理对象墙砖为目标对象类型的话，代理类就必须实现接口才能类型强转
        o.login();


        /**         *获取动态代理类的class字节码         */
        byte[] classFile = ProxyGenerator.generateProxyClass("Proxy0", UserLogin.class.getInterfaces());
        /**         *在当前的工程目录下保存文件         */
        FileOutputStream fos =new FileOutputStream(new File("Proxy0.class"));
        fos.write(classFile);
        fos.flush();
        fos.close();
    }
}
