package thread;

import java.util.concurrent.*;

public class ThreadDestroyTest {

    public static void main(String[] args) {
        ThreadPoolExecutor ex = new ThreadPoolExecutor(1,
                2,
                1,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(2));

        // 提交10个任务
        for(int i=0; i < 10; i++){

            ex.submit(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
