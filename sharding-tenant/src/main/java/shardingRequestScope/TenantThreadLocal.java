package shardingRequestScope;

public class TenantThreadLocal {

    static ThreadLocal<String> tl = new ThreadLocal<String>();

    public static String getTenant(){
        return tl.get();
    }
}
