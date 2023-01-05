package part2;

import java.util.Objects;
import java.util.concurrent.*;

public class test {

    public static void main(String[] args){
        CustomExecutor customExecutor = new CustomExecutor();

        var task = new TaskClass(()->{
            int sum = 0;
            for (int i = 1; i <= 10; i++) {
                sum += i;
            }

            return sum;

        },TaskType.COMPUTATIONAL);

        Future sumTask = customExecutor.submit(task);

        final int sum;

        try {
            sum = (int)sumTask.get(1, TimeUnit.MILLISECONDS);
        }catch (InterruptedException | ExecutionException | TimeoutException e){
            throw new RuntimeException(e);
        }

        System.out.println("Sum of 1 through 10 = " + sum);

        Callable<Double> callable1 = ()-> {
            return 1000 * Math.pow(1.02, 5);
        };
        Callable<String> callable2 = ()-> {
            StringBuilder sb = new StringBuilder("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
            return sb.reverse().toString();
        };

        Future priceTask = customExecutor.submit(()-> {
            double ans = 1000 * Math.pow(1.02, 5);
        }, TaskType.COMPUTATIONAL);
        Future reverseTask = customExecutor.submit(callable2, TaskType.IO);
        final Double totalPrice;
        final String reversed;
        try {
            totalPrice = (double)priceTask.get();
            reversed = String.valueOf(reverseTask.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Reversed String = " + reversed);
        System.out.println(String.valueOf("Total Price = " + totalPrice));
        System.out.println("Current maximum priority = " + customExecutor.getCurrentMax());
        customExecutor.gracefullyTerminate();

    }
}
