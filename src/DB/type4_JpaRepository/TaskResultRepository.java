package DB.type4_JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface TaskResultRepository extends JpaRepository<TaskResult4, Long> {

    TaskResult4 findByTaskId (int id);


}
