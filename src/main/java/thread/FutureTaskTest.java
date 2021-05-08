package thread;

import java.util.concurrent.*;

public class FutureTaskTest {

    public static void main(String[] args) {
        ExecutorService ex = new ThreadPoolExecutor(1,2,2,TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2));

        ex.submit(new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return null;
            }
        }));

        ex.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(1/0);
            }
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("sdf");


        ex.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(1/1);
            }
        });
    }
}
