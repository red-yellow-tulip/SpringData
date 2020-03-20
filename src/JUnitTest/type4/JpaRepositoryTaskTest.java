package JUnitTest.type4;

import DB.type4_JpaRepository.ConfDataSource;
import DB.type4_JpaRepository.JpaRepositoryTask;
import DB.type4_JpaRepository.TaskResult4;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertNotNull;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JpaRepositoryTaskTest {


    private static GenericApplicationContext ctx;

    private static JpaRepositoryTask jpaRepositoryTask;

    private static int task_id;
    private static Random r = new Random();



    @Before
    public void after (){

    }

    @Test
    public void AfindAll() {

        task_id = r.nextInt(1000);
        ctx = new AnnotationConfigApplicationContext(ConfDataSource.class);
        jpaRepositoryTask = ctx.getBean(JpaRepositoryTask.class);
        assertNotNull(jpaRepositoryTask);

        List<TaskResult4> list = jpaRepositoryTask.findAll();
        assertNotNull(list);

        System.out.println("======Hibernate.findAll======="+task_id);
        list.forEach(System.out::println);

    }

    @Test
    public void Binsert() {

        System.out.println("======Hibernate.insert======="+task_id);
        TaskResult4 pr = new TaskResult4(task_id,7,8);

        int p = jpaRepositoryTask.insert(pr);
        assert(p == 0 );

        List<TaskResult4> list = jpaRepositoryTask.findAll();
        list.forEach(System.out::println);

    }

    @Test
    public void CfindById() {

        System.out.println("======Hibernate.findAll======="+task_id);
        TaskResult4 t = jpaRepositoryTask.findById(task_id);
        assertNotNull(t);

        System.out.println(t);

    }


    @Test
    public void Ddelete() {

        System.out.println("======Hibernate.delete======="+task_id);
        int p = jpaRepositoryTask.delete(task_id);
        assert(p != -1);

        List<TaskResult4> list = jpaRepositoryTask.findAll();
        list.forEach(System.out::println);

    }



    @After
    public void afterTest(){


    }
}