package part2;

import java.util.Objects;
import java.util.concurrent.Callable;

public class TaskClass implements Callable {
    private TaskType tasktype;
    private Object obj = null;

    public TaskClass(Callable task, TaskType tasktype = null){
        this.obj = task;
        this.tasktype = tasktype;
    }

    public TaskClass(Callable task){
        this(task,TaskType.COMPUTATIONAL);
    }

    public int compare(TaskClass t1, TaskClass t2){
        if(t1.tasktype.equals(t2.tasktype)){
            return 0;
        }

        if(t1.tasktype.getPriorityValue() > t2.tasktype.getPriorityValue()){
            return 1;
        }else{
            return -1;
        }
    }

    @Override
    public Object call() throws Exception {
        return this.obj;
    }
}
