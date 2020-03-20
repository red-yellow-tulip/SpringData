package JUnitTest.type1;

import DB.type1_JdbcTemplate.ConfDataSource;
import DB.type1_JdbcTemplate.JdbcTemplateTask;
import DB.type1_JdbcTemplate.TaskResult;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import static org.junit.Assert.assertNotNull;

@FixMethodOrder (MethodSorters.NAME_ASCENDING)
public class JdbcTemplateTaskTest {

    private GenericApplicationContext ctx;
    private static JdbcTemplateTask jdbcTemplateTask;

    private static int task_id;
    private static Random r = new Random();

    @Before
    public void testJDBCTemplate() {

    }

    @Test
    public void AtestJDBCTemplateInsert() {

        ctx = new AnnotationConfigApplicationContext(ConfDataSource.class);
        jdbcTemplateTask = ctx.getBean(JdbcTemplateTask.class);
        assertNotNull(jdbcTemplateTask);

        task_id = r.nextInt(1000);

        TaskResult tr = new TaskResult(0L, task_id,7L,"",  (Long[]) Arrays.asList(15L,16L,17L,18L,19L,110L,111L,112L).toArray());
        int count = jdbcTemplateTask.insert(tr);
        System.out.println("id=" + task_id);

        // findAll
        System.out.println("======testJDBCTemplate.All=======print");
        List<TaskResult> list  = jdbcTemplateTask.findAll();
        for (TaskResult t: list)
            System.out.println(t);

        assert (count == list.size());
    }


    @Test
    public void BtestJDBCTemplateFindById() {

        // findById
        System.out.println("Task id = " + task_id);
        TaskResult t = jdbcTemplateTask.findById(task_id);
        assertNotNull(t);

        System.out.println("======testJDBCTemplate.FindById=======print");
        System.out.println(t);
    }

    @Test
    public void CtestJDBCTemplateUpdate() {

        // update
        TaskResult tr = new TaskResult(0L, task_id,17L,"",  (Long[]) Arrays.asList(115L,116L,117L,118L,119L,1110L,1111L,1112L).toArray());
        int count = jdbcTemplateTask.update(tr);
        assert (count == 1);

        TaskResult t = jdbcTemplateTask.findById(task_id);
        assertNotNull(t);

        System.out.println("======testJDBCTemplate.Update=======print");
        System.out.println(t);

    }

    @Test
    public void DtestJDBCTemplateDelete() {

        // delete
        int count = jdbcTemplateTask.delete(task_id);
        assert (count == 1);

        System.out.println("======testJDBCTemplate.Delete=======print");
        List<TaskResult> list  = jdbcTemplateTask.findAll();
        for (TaskResult t: list)
            System.out.println(t);

        assert (0 == list.size());
    }



    @After
    public void testJDBCTemplateClose() {

    }
}