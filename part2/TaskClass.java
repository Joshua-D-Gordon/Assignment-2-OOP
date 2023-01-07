package part2;

import java.util.Comparator;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadFactory;

public class TaskClass implements Callable, Comparable<TaskClass>, ThreadFactory{
    //TaskClass priority variable of type enum TaskType
    public TaskType priority;
    //TaskClass's callable variable of type Callable for returning a Object
    private Callable callable;
    // TaskClasses comparator variable cmp. this shows how we compare are TaskClass with other TaskClass's instances
    private static Comparator<TaskClass> cmp = new Comparator<TaskClass>() {
        @Override
        public int compare(TaskClass o1, TaskClass o2) {
            return o1.priority.getPriorityValue()-o2.priority.getPriorityValue();
        }
    };

    /**
     * getter for TaskClass comparator cmp.
     * @return - cmp
     */
    public static Comparator<TaskClass> getCmp() {
        return cmp;
    }

    /**
     * constructor for TaskClass
     * @param callable - a variable of type Callable
     * @param tasktype - a number representing a value ina enum of TaskType
     */
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

    /**
     * constructor for TaskType with no TaskType Value
     * @param callable - a variable of type Callable
     */
    public TaskClass(Callable callable){
        this(callable,null);
    }

    /**
     * a method for creating a new TaskClass instance
     * @param callable - a variable of type Callable
     * @param priority - a number representing a value in a enum of TaskType as priority
     * @return - a new instance of TaskClass
     */
    public static TaskClass createTask(Callable callable, TaskType priority) {
        return new TaskClass(callable, priority);
    }

    /**
     * our overridden method for interface Callable
     * @return - a object returned
     * @throws Exception - an execption thrown
     */
    @Override
    public Object call() throws Exception {
        try {
            return callable.call();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * overiden method for interface comparator
     * @param o the object to be compared.
     * @return - the diffrence between two TaskClass's prioroties
     */
    @Override
    public int compareTo(TaskClass o) {
        return this.priority.getPriorityValue() - this.priority.getPriorityValue();
    }

    /**
     * Custom thread factory method
     * @param callable - callable variable
     * @return - returns a new custom thread
     */
    public Thread newThread(Callable callable){
        Thread customThread = new Thread((Runnable) callable);
        customThread.setPriority(this.priority.getPriorityValue());
        return customThread;
    }

    /**
     * overiden thread factory method
     * @param r a runnable to be executed by new thread instance
     * @return - a new custom thread
     */
    @Override
    public Thread newThread(Runnable r) {
        Thread customThread = new Thread();
        customThread.setPriority(this.priority.getPriorityValue());
        return customThread;
    }

}
