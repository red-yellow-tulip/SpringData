package JUnitTest.type2;

import DB.type2_SessionFactory.ConfDataSource;
import DB.type2_SessionFactory.HibernateTask;
import DB.type2_SessionFactory.TaskResult2;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.After;
import org.junit.runners.MethodSorters;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import java.util.List;
import java.util.Random;
import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HibernateTaskTest {

    private static HibernateTask hibernatyTask;
    private static GenericApplicationContext ctx;

    private static int task_id;
    private static Random r = new Random();

    @Before public void after (){

    }

    @Test
    public void AfindAll() {

        task_id = r.nextInt(1000);

        ctx = new AnnotationConfigApplicationContext(ConfDataSource.class); //
        hibernatyTask = ctx.getBean(HibernateTask.class);
        assertNotNull(hibernatyTask);

        List<TaskResult2> list = hibernatyTask.findAll();
        assertNotNull(list);

        System.out.println("======Hibernate.findAll======="+task_id);
        list.forEach(System.out::println);
    }

    @Test
    public void Binsert() {

        System.out.println("======Hibernate.insert======="+task_id);
        TaskResult2 pr = new TaskResult2(task_id,7, 99);

        int p = hibernatyTask.insert(pr);
        assert(p == 0);

        List<TaskResult2> list = hibernatyTask.findAll();
        list.forEach(System.out::println);
    }

    @Test
    public void CfindById() {

        System.out.println("======Hibernate.findAll======="+task_id);
        TaskResult2 t = hibernatyTask.findById(task_id);
        assertNotNull(t);

        System.out.println(t);
    }


    @Test
    public void DDelete() {

        System.out.println("======Hibernate.delete======="+task_id);
        int p = hibernatyTask.delete(task_id);
        assert(p != -1);

        List<TaskResult2> list = hibernatyTask.findAll();
        list.forEach(System.out::println);
    }



    @After
    public void afterTest(){

    }
}