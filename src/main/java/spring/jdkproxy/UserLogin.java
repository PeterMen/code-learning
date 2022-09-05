package spring.jdkproxy;

/**
 * 被增强的对象
 * */
public class UserLogin implements UserLoginInterface{

    @Override
    public void login(){
        System.out.println("-------登录成功-----");
    }
}
