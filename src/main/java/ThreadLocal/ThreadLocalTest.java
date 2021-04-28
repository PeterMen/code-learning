package ThreadLocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalTest {

    public static void main1(String[] args) {
        ExecutorService exe = Executors.newFixedThreadPool(1);
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            exe.submit(() -> {
                System.out.println(Thread.currentThread().getName());
                UserHandler.set("uuuu");

            });
//            UserHandler.get();
        }
    }

    public static void main(String[] args) {
        float f = 3.4F;
    }
}
