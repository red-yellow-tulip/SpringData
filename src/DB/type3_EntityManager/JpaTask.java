package DB.type3_EntityManager;

import DB.Interface.ITaskResult;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


@ComponentScan ({"DB.type3_EntityManager"})
@Configuration
public class JpaTask implements ITaskResult<TaskResult3> {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional (readOnly = true)
    public List<TaskResult3> findAll() {
        return entityManager.createNamedQuery(TaskResult3.Find_All,TaskResult3.class).getResultList();
    }

    @Override
    @Transactional (readOnly = true)
    public TaskResult3 findById(int id) {
        return entityManager.createNamedQuery(TaskResult3.FindById,TaskResult3.class).setParameter(1,id).getSingleResult();

    }

    @Override
    @Transactional
    public int insert(TaskResult3 t) {
        Session s  = entityManager.unwrap(Session.class);
        return (int) s.save(t);
    }

    @Override
    @Transactional
    public int update(TaskResult3 t) {
        entityManager.persist(t);
        return 0;
    }

    @Override
    @Transactional
    public int delete(int id) {
        TaskResult3 tmp = findById(id);
        if (tmp == null)
            return -1;
        entityManager.remove(tmp);
        return 0;
    }
}
