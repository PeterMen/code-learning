package sharding;

public class ThreadLocalTenant {
    public static ThreadLocal<String> tenant = new ThreadLocal<String>();

    public static String getTenant(){
        return tenant.get();
    }
}
