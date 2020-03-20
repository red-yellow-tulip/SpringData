package JUnitTest.type3;

import DB.type3_EntityManager.*;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import java.util.List;
import java.util.Random;
import static org.junit.Assert.assertNotNull;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JpaTaskTest {

    private static JpaTask jpaTask;
    private static GenericApplicationContext ctx;

    private static int task_id;
    private static Random r = new Random();

    @Before
    public void after (){

    }

    @Test
    public void AfindAll() {

        task_id = r.nextInt(1000);
        ctx = new AnnotationConfigApplicationContext(ConfDataSource.class);
        jpaTask = ctx.getBean(JpaTask.class);
        assertNotNull(jpaTask);

        List<TaskResult3> list = jpaTask.findAll();
        assertNotNull(list);

        System.out.println("======Hibernate.findAll======="+task_id);
        list.forEach(System.out::println);

    }

    @Test
    public void Binsert() {

        System.out.println("======Hibernate.insert======="+task_id);
        TaskResult3 pr = new TaskResult3(task_id,7,8);

        int p = jpaTask.insert(pr);
        assert(p > 0 );

        List<TaskResult3> list = jpaTask.findAll();
        list.forEach(System.out::println);

    }

    @Test
    public void CfindById() {

        System.out.println("======Hibernate.findAll======="+task_id);
        TaskResult3 t = jpaTask.findById(task_id);
        assertNotNull(t);

        System.out.println(t);

    }


    @Test
    public void Ddelete() {

        System.out.println("======Hibernate.delete======="+task_id);
        int p = jpaTask.delete(task_id);
        assert(p != -1);

        List<TaskResult3> list = jpaTask.findAll();
        list.forEach(System.out::println);

    }



    @After
    public void afterTest(){


    }
}