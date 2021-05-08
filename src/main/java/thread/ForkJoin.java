package thread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

public class ForkJoin extends RecursiveTask<List<Integer>> {

    List<Integer> sortList;

    public ForkJoin(List<Integer> workNoList) {
        this.sortList = workNoList;
    }

    @Override
    protected List<Integer> compute() {

        // 任务达到最小粒度，不需要拆分，直接执行
        if (sortList.size() > 0 && sortList.size() <= 3) {
            Collections.sort(sortList);
            return sortList;
        }

        // 当前任务拆分位3个子任务
        int n = sortList.size()/3;
        List<Integer> list1 = sortList.subList(0, n);
        List<Integer> list2 = sortList.subList(n, 2*n);
        List<Integer> list3 = sortList.subList(2*n, sortList.size());

        ForkJoin t1 = new ForkJoin(list1);
        ForkJoin t2 = new ForkJoin(list2);
        ForkJoin t3 = new ForkJoin(list3);

        t1.fork();

        t2.fork();

        t3.fork();
        // 执行3个子任务
//        invokeAll(t1, t2, t3);

        // join等待任务执行完成
        List<Integer> result1 = t1.join();
        List<Integer> result2 = t2.join();
        List<Integer> result3 = t3.join();

        List<Integer> result = new ArrayList<>();
        int i1= 0, i2= 0, i3 = 0;
        Integer num1, num2, num3;
        do{
            if(i1 == result1.size()) num1 = Integer.MAX_VALUE;
            else num1 = result1.get(i1);
            if(i2 == result2.size()) num2 = Integer.MAX_VALUE;
            else num2 = result2.get(i2);
            if(i3 == result3.size()) num3 = Integer.MAX_VALUE;
            else num3 = result3.get(i3);
            if (num1 <= num2 && num1 <= num3) {
                result.add(num1);
                i1++;
            }
            if (num2 <= num1 && num2 <= num3) {
                result.add(num2);
                i2++;
            }
            if (num3 <= num2 && num3 <= num1) {
                result.add(num3);
                i3++;
            }
        } while (i1 < result1.size() || i2 < result2.size() || i3 < result3.size());
        return result;

    }
}


