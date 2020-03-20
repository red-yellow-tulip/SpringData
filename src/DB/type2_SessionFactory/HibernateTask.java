package DB.type2_SessionFactory;

import DB.Interface.ITaskResult;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@ComponentScan ({"DB.type2_SessionFactory"})
@Configuration
public class HibernateTask implements ITaskResult<TaskResult2> {

    private static  final String select = "from TaskResult2";
    private static  final String selectId = "from TaskResult2  t where t.task_id = :param";

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional (readOnly = true)
    public List<TaskResult2> findAll() {

        return sessionFactory.openSession().createQuery(select).list();
    }

    @Override
    @Transactional (readOnly = true)
    public TaskResult2 findById(int id) {

        Optional<TaskResult2> op = sessionFactory.openSession().createQuery(selectId).setParameter("param", (long) id).uniqueResultOptional();
        return op.orElse(null);
    }

    @Override
    @Transactional
    public int insert(TaskResult2 t) {
        sessionFactory.openSession().save(t);
        return 0;
    }

    @Override
    @Transactional
    public int update(TaskResult2 t) {
        sessionFactory.openSession().update(t);
        return 0;
    }

    @Override
    @Transactional
    public int delete(int id) {
        TaskResult2 t = findById(id);
        if (t == null)
            return -1;
        sessionFactory.openSession().delete(t);
        return 0;
    }
}
