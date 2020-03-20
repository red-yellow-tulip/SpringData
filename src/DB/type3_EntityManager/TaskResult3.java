package DB.type3_EntityManager;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

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

@NamedQueries( { @NamedQuery(name = TaskResult3.Find_All, query = "from TaskResult3 t" ),
                 @NamedQuery(name = TaskResult3.FindById, query = "from TaskResult3 t where t.task_id = ?1" )
})


public class TaskResult3 implements Serializable  {

    static final String Find_All = "JPASpringTask.findAll";
    static final String FindById = "JPASpringTask.FindById";

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private int id;

    @Column (name = "task_id")
    private int task_id;

    @Column (name = "arg2")
    private int arg2;

    @Column (name = "arg3")
    private int arg3;

    public TaskResult3() {

    }

    public TaskResult3(int task_id, int arg2, int arg3) {

        this.task_id = task_id;
        this.arg2 = arg2;
        this.arg3 = arg3;

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public void setArg2(int arg2) {
        this.arg2 = arg2;
    }

    public void setArg3(int arg3) {
        this.arg3 = arg3;
    }

    @Override
    public String toString() {
        return "TaskResult3{" +
                "id=" + id +
                ", task_id=" + task_id +
                ", arg2=" + arg2 +
                ", arg3=" + arg3 +
                '}';
    }
}
