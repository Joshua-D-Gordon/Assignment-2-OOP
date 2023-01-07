package part2;

import java.util.Comparator;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadFactory;

public class TaskClass implements Callable, Comparable<TaskClass>, ThreadFactory{
    public TaskType priority;
    private Callable callable;

    private static Comparator<TaskClass> cmp = new Comparator<TaskClass>() {
        @Override
        public int compare(TaskClass o1, TaskClass o2) {
            return o1.priority.getPriorityValue()-o2.priority.getPriorityValue();
        }
    };

    public static Comparator<TaskClass> getCmp() {
        return cmp;
    }

    public TaskClass(Callable callable, TaskType tasktype){
        try {
            this.callable = callable;
            this.priority = tasktype;
        }catch (IllegalArgumentException e){
            this.callable = callable;
            this.priority = TaskType.OTHER;
            e.printStackTrace();
        }
    }

    public TaskClass(Callable callable){
        this(callable,null);
    }

    public static TaskClass createTask(Callable callable, TaskType priority) {
        return new TaskClass(callable, priority);
    }

    @Override
    public Object call() throws Exception {
        try {
            return callable.call();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int compareTo(TaskClass o) {
        return this.priority.getPriorityValue() - this.priority.getPriorityValue();
    }

    public Thread newThread(Callable callable){
        Thread customThread = new Thread((Runnable) callable);
        customThread.setPriority(this.priority.getPriorityValue());
        return customThread;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread customThread = new Thread();
        customThread.setPriority(this.priority.getPriorityValue());
        return customThread;
    }

}
