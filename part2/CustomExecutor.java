package part2;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

public class CustomExecutor {
    private ThreadPoolExecutor executor;
    private PriorityBlockingQueue queue;
    private int numProcessors, poolSize, maxPoolSize, maxPriority;
    private long keepAliveTime;
    private TimeUnit unit;

    /**
     * constructor
     */
    public CustomExecutor(){
        this.numProcessors = Runtime.getRuntime().availableProcessors();
        this.poolSize = numProcessors / 2;
        this.maxPoolSize = numProcessors - 1;
        this.keepAliveTime = 300;
        this.unit = TimeUnit.MILLISECONDS;
        //the priority queue with TaskClass's comparator
        this.queue = new PriorityBlockingQueue(maxPoolSize, TaskClass.getCmp());
        //the threadpool of our executor
        this.executor = new ThreadPoolExecutor(poolSize, maxPoolSize, keepAliveTime, unit, queue);
    }

    /**
     * shouts down the customexecutor and lets threadpool finish operations
     */
    public void shutdown() {
        this.executor.shutdown();
    }

    /**
     * shuts down the customexecutor while trying to shut down each thread in the threadpool
     * @return
     */
    public List<Runnable> shutdownNow() {
        return this.executor.shutdownNow();
    }

    /**
     * returns executor is shutdown
     * @return - true or false
     */
    public boolean isShutdown() {
        return this.executor.isShutdown();
    }

    /**
     * returns executor is terminated
     * @return - true or false
     */
    public boolean isTerminated() {
        return this.executor.isTerminated();
    }

    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return this.executor.awaitTermination(timeout, unit);
    }

    /**
     * returns max priority in the current queue
     * @return - max priority
     */
    public int getCurrentMax(){
        return this.maxPriority;
    }

    /**
     * submits a instance of TaskClass to queue
     * @param task - instance of TaskClass
     * @return - returns a future
     * @param <T> - ignore nulls
     */
    public <T> Future<T> submit(TaskClass task){
        maxPriority = Math.max(maxPriority, task.priority.getPriorityValue());
        return this.executor.submit(task);
    }

    /**
     * returns a future of a submited taskClass instance with no priority to the queue
     * @param task - callabe of taskclass instance to submit
     * @return - future
     * @param <T>  - ignore nulls
     */
    public <T> Future<T> submit(Callable task) {
        TaskClass taskNew = TaskClass.createTask(task,TaskType.OTHER);
        return (Future<T>) this.submit(taskNew);
    }

    /**
     * returns a future of a submited taskClass instance with a priority to the queue
     * @param task - callabe of taskclass instance to submit
     * @param priority - priority of taskclass instance to submit
     * @return - future
     * @param <T> - ignor nulls
     */
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

    /**
     * executes this executor
     * @param command - runable comand
     */
    public void execute(Runnable command) {
        this.executor.execute(command);
    }
    // same as shotdown
    public void gracefullyTerminate(){
        this.shutdown();
    }
}
