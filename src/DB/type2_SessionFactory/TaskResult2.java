package DB.type2_SessionFactory;

import javax.persistence.*;
import java.io.Serializable;

/*
create table  task_array
(
    id  serial PRIMARY KEY NOT NULL,
    arrId int NOT NULL,
    arr   int,
    task_id int NOT NULL
);
*/

@Entity
@Table (name = "task_result2")
public class TaskResult2 implements Serializable  {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private long id;

    @Column (name = "task_id")
    private long task_id;

    @Column (name = "arg2")
    private long arg2;

    @Column (name = "arg3")
    private long arg3;

    public TaskResult2(){

    }

    public TaskResult2(long task_id, long arg2, long arg3) {
        this.task_id = task_id;
        this.arg2 = arg2;
        this.arg3 = arg3;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTask_id() {
        return task_id;
    }

    @Override
    public String toString() {
        return "TaskResult{" +
                "task_id=" + task_id +
                ", arg2=" + arg2 +
                ", arg3=" + arg3 +
                '}';
    }

    public void setTask_id(long task_id) {
        this.task_id = task_id;
    }

    public long getArg2() {
        return arg2;
    }

    public void setArg2(long arg2) {
        this.arg2 = arg2;
    }

    public long getArg3() {
        return arg3;
    }

    public void setArg3(long arg3) {
        this.arg3 = arg3;
    }




}
