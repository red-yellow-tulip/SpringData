package DB.type1_JdbcTemplate;

import java.io.Serializable;

public class TaskResult implements Serializable {

    private Long id;
    private int task_id;
    private Long arg;
    private String nameTask;
    private Long[] arr;

    public TaskResult(Long id, int task_id, Long arg, String nameTask, Long[] arr) {
        this.id = id;
        this.task_id = task_id;
        this.arg = arg;
        this.nameTask = nameTask;
        this.arr = arr;
    }

    @Override
    public String toString() {
        return "TaskResult{" +
                "task_id=" + id +
                ", arg2=" + task_id +
                ", arg3=" + arg +
                ", nameTask='" + nameTask + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public int getTask_id() {
        return task_id;
    }

    public Long getArg() {
        return arg;
    }

    public String getNameTask() {
        return nameTask;
    }

    public Long[] getArr() {
        return arr;
    }
}
