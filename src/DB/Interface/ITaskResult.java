package DB.Interface;

import java.util.List;

public interface ITaskResult<T> {

    List<T> findAll();
    T findById(int id);
    int insert (T t);
    int update (T t);
    int delete (int id);

}
