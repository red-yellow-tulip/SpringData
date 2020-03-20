package DB.type4_JpaRepository;

import javax.persistence.*;
import java.io.Serializable;

/*
create table  task_result2
(
    id  serial PRIMARY KEY NOT NULL,
    task_id int NOT NULL UNIQUE,
    arg2    int,
    arg3    int
        );

INSERT  into task_result2 (task_id,arg2,arg3) VALUES (2,2,3);
SELECT * FROM task_result2;
*/

@Entity
@Table (name = "task_result2")
public class TaskResult4 implements Serializable  {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private int id;

    @Column (name = "task_id")
    private int taskId;

    @Column (name = "arg2")
    private int arg2;

    @Column (name = "arg3")
    private int arg3;

    public TaskResult4() {

    }

    public TaskResult4(int task_id, int arg2, int arg3) {

        this.taskId = task_id;
        this.arg2 = arg2;
        this.arg3 = arg3;

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTask_id(int task_id) {
        this.taskId = task_id;
    }

    public void setArg2(int arg2) {
        this.arg2 = arg2;
    }

    public void setArg3(int arg3) {
        this.arg3 = arg3;
    }

    @Override
    public String toString() {
        return "TaskResult4{" +
                "id=" + id +
                ", task_id=" + taskId +
                ", arg2=" + arg2 +
                ", arg3=" + arg3 +
                '}';
    }
}
