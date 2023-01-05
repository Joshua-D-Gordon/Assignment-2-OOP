package part2;


import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

public class CustomExecutor extends Executors implements ExecutorService {


    @Override
    public void shutdown() {
        this.shutdown();
    }

    @Override
    public List<Runnable> shutdownNow() {
        return this.shutdownNow();
    }

    @Override
    public boolean isShutdown() {
        return this.isShutdown();
    }

    @Override
    public boolean isTerminated() {
        return this.isTerminated();
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return this.awaitTermination(timeout, unit);
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        return this.submit(task);
    }

    @Override
    public <T> Future<T> submit(Runnable task, T result) {
        return this.submit(task, result);
    }

    @Override
    public Future<?> submit(Runnable task) {
        return this.submit(task);
    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
        return this.invokeAll(tasks);
    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException {
        return this.invokeAll(tasks, timeout, unit);
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
        return this.invokeAny(tasks);
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return this.invokeAny(tasks, timeout, unit);
    }

    @Override
    public void execute(Runnable command) {
        this.execute(command);
    }
}
