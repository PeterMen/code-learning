package ThreadLocal;

public class UserHandler {

    private static final ThreadLocal<String> user = new ThreadLocal<>();

    public static void set(String userName){
        user.set(userName);
        System.out.println("user设置成功" );

    }

    public static String get(){
        System.out.println("user获取成功，值为： " + user.get());
        return user.get();
    }

}
