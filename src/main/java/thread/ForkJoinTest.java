package thread;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class ForkJoinTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);

        ForkJoin demo = new ForkJoin(list);

        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<List<Integer>> t = pool.submit(demo);
//        List<Integer> result = demo.compute();

        System.out.println(t.get());
    }
}
