package DB.type4_JpaRepository;

import DB.Interface.ITaskResult;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;


@ComponentScan ({"DB.type4_JpaRepository"})
@Configuration
public class JpaRepositoryTask implements ITaskResult<TaskResult4> {

    @Resource   // таблица продукт
    private TaskResultRepository taskResultRepository;

    @Override
    @Transactional (readOnly = true)
    public List<TaskResult4> findAll() {
        return taskResultRepository.findAll();
    }

    @Override
    @Transactional (readOnly = true)
    public TaskResult4 findById(int id) {
        return taskResultRepository.findByTaskId(id);

    }

    @Override
    @Transactional
    public int insert(TaskResult4 t) {
        taskResultRepository.save(t);
        return 0;
    }

    @Override
    @Transactional
    public int update(TaskResult4 t) {
        taskResultRepository.save(t);
        return 0;
    }

    @Override
    @Transactional
    public int delete(int id) {
        TaskResult4 tmp = findById(id);
        if (tmp == null)
            return -1;
        taskResultRepository.delete(tmp);
        return 0;
    }
}
