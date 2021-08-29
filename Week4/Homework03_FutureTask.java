package liuzm.conc0303;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Author: liuzm
 * @Date: 2021-08-30 00:33:50
 * @Description: liuzm.conc0303
 * @version: 1.1
 */
public class Homework03_FutureTask {

    public static void main(String[] args) throws InterruptedException,ExecutionException {
        long start = System.currentTimeMillis();

        Homework03_FutureTask demo = new Homework03_FutureTask();

        FutureTask<Integer> task = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return demo.sum();
            }
        });
        new Thread(task).start();
        System.out.println(Thread.currentThread().getName() + "异步计算的结果为：" + task.get());
        System.out.println(Thread.currentThread().getName() + "使用时间：" + (System.currentTimeMillis() - start) + " ms");
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if (a < 2)
            return 1;
        return fibo(a - 1) + fibo(a - 2);
    }
}
