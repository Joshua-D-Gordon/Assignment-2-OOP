package part2;

import com.sun.source.util.TaskListener;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.*;

public class CustomExecutor {

    private ThreadPoolExecutor executor;
    private PriorityBlockingQueue queue;
    private int numProcessors, poolSize, maxPoolSize, maxPriority;
    private long keepAliveTime;
    private TimeUnit unit;

    public CustomExecutor(){
        this.numProcessors = Runtime.getRuntime().availableProcessors();
        this.poolSize = numProcessors / 2;
        this.maxPoolSize = numProcessors - 1;
        this.keepAliveTime = 300;
        this.unit = TimeUnit.MILLISECONDS;
        this.queue = new PriorityBlockingQueue(maxPoolSize, TaskClass.getCmp());
        this.executor = new ThreadPoolExecutor(poolSize, maxPoolSize, keepAliveTime, unit, queue);
    }

    public void shutdown() {
        this.executor.shutdown();
    }

    public List<Runnable> shutdownNow() {
        return this.executor.shutdownNow();
    }
    public boolean isShutdown() {
        return this.executor.isShutdown();
    }

    public boolean isTerminated() {
        return this.executor.isTerminated();
    }
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return this.executor.awaitTermination(timeout, unit);
    }

    public int getCurrentMax(){
        return this.maxPriority;
    }

    public <T> Future<T> submit(TaskClass task){
        maxPriority = Math.max(maxPriority, task.priority.getPriorityValue());
        return this.executor.submit(task);
    }
    public <T> Future<T> submit(Callable task) {
        TaskClass taskNew = TaskClass.createTask(task,TaskType.OTHER);
        return (Future<T>) this.submit(taskNew);
    }

    public <T> Future<T> submit(Callable task, TaskType priority) {
        TaskClass taskNew = TaskClass.createTask(task, priority);
        return (Future<T>) this.submit(taskNew);
    }

    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
        return this.executor.invokeAll(tasks);
    }
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException {
        return this.executor.invokeAll(tasks, timeout, unit);
    }
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
        return this.executor.invokeAny(tasks);
    }
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return this.executor.invokeAny(tasks, timeout, unit);
    }

    public void execute(Runnable command) {
        this.executor.execute(command);
    }

    public void gracefullyTerminate(){
        this.shutdown();
    }
}
