package part2;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.*;

public class CustomExecutor{

    private Executor executor;
    private PriorityBlockingQueue queue;
    private int numProcessors, poolSize, maxPoolSize, maxPriority = 10;
    private long keepAliveTime;
    private TimeUnit unit;
    private HashMap<TaskClass, Integer> maxpriorityHashmap;

    public CustomExecutor(){
        this.numProcessors = Runtime.getRuntime().availableProcessors();
        this.poolSize = numProcessors / 2;
        this.maxPoolSize = numProcessors - 1;
        this.keepAliveTime = 300;
        this.unit = TimeUnit.MILLISECONDS;
        this.queue = new PriorityBlockingQueue(maxPriority, TaskClass.getCmp());
        this.executor = new ThreadPoolExecutor(poolSize, maxPoolSize, keepAliveTime, unit, queue);
        this.maxpriorityHashmap = new HashMap(queue.size());
    }

    public void shutdown() {
        this.shutdown();
    }

    public List<Runnable> shutdownNow() {
        return this.shutdownNow();
    }
    public boolean isShutdown() {
        return this.isShutdown();
    }

    public boolean isTerminated() {
        return this.isTerminated();
    }
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return this.awaitTermination(timeout, unit);
    }
    public int getCurrentMax(){
        int max = -1;
        for(int i = 0; i< this.maxpriorityHashmap.size();i++){
            if(max < this.maxpriorityHashmap.get(i)){
                max = this.maxpriorityHashmap.get(i);
            }
        }
        return max;
    }
    public void whensubmited(TaskClass task){
        if(!this.maxpriorityHashmap.isEmpty()){
            //remove function from hashmap
        }
        this.maxpriorityHashmap.put(task,task.priority.getPriorityValue());
    }
    public void submit(TaskClass task){
        whensubmited(task);
        this.executor.execute(()->{
            try{
                task.call();
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }
    public TaskClass submit(Callable task) {
        whensubmited((TaskClass) task);
        return new TaskClass(task);
    }

    public TaskClass submit(Callable task, TaskType priority) {
        whensubmited((TaskClass) task);
        return new TaskClass(task,priority);
    }

    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
        return this.invokeAll(tasks);
    }
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException {
        return this.invokeAll(tasks, timeout, unit);
    }
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
        return this.invokeAny(tasks);
    }
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return this.invokeAny(tasks, timeout, unit);
    }

    public void execute(Runnable command) {
        this.executor.execute(command);
    }

    public void gracefullyTerminate(){
        this.shutdownNow();
        if(this.isShutdown()){
            try{
                this.awaitTermination(300, TimeUnit.MILLISECONDS);
            }
            catch(Exception e){
                this.gracefullyTerminate();
            }
        }
    }
}
