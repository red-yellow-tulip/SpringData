package DB.type1_JdbcTemplate;

import DB.Interface.ITaskResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
create table  task_result
(
    id  serial PRIMARY KEY NOT NULL,
    task_id double precision,
    arg2    bigint,
    arg3    varchar(256),
    res bigint[]  NOT NULL
);
*/

@ComponentScan ({"DB.type1_JdbcTemplate"})
@Configuration
public class JdbcTemplateTask implements ITaskResult<TaskResult> {

    private JdbcTemplate jdbcTemplate;

    private final static String select =   "select * from task_result;";
    private final static String selectId = "select * from task_result t  where t.task_id = %d;";
    private final static String insrert =  "insert  into task_result (task_id,arg2,arg3,res) VALUES (?,?,?,?);";
    private final static String update =   "update task_result set arg2 = ?, arg3 = ?, res = ? where task_id = ?;";
    private final static String delete =   "delete from task_result t where t.task_id = %d;";

    @Autowired
    public void setJdbcTemplate(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
    }

    @Bean
    public JdbcTemplate getJdbcTemplateTask() {
        return jdbcTemplate;
    }

    @Override
    public List<TaskResult> findAll() {

        List<TaskResult> res = jdbcTemplate.query(select, new TaskRowMapper());

        if (res == null){
            System.out.println("Запрос не вернул результатов:" );
            return null;
        }
        return res;
    }

    @Override
    public TaskResult findById(int id) {

        final String sql = String.format(selectId,id);
        List<TaskResult> res = jdbcTemplate.query(sql, new TaskRowMapper());

        if (res == null){
            System.out.println("Запрос не вернул результатов:" );
            res =  new ArrayList<TaskResult>();
        }
        return res.size() > 0 ? res.get(0): null;
    }

    @Override
    public int insert(TaskResult t) {

        try (Connection connection = jdbcTemplate.getDataSource().getConnection();) {

            PreparedStatement statement = connection.prepareStatement(insrert);

            statement.setInt(1, t.getTask_id());
            statement.setLong(2, t.getArg());
            statement.setString(3, t.getNameTask());
            statement.setArray(4, connection.createArrayOf("bigint", t.getArr()));
            statement.addBatch();

            int executeBatch = statement.executeUpdate();

            System.out.println("Изменено строк " + executeBatch);
           /* ResultSet keys = statement.getGeneratedKeys();
            int a = keys.getMetaData().getColumnCount();
            while (keys.next())
                System.out.println("new id: " + keys.getLong(1));*/

           return executeBatch;

        } catch (SQLException e) {
            System.out.println("Error execute statement..." + e.getMessage());
            return 0;
            // e.printStackTrace();
        }

    }

    @Override
    public int update(TaskResult t) {

        try (Connection connection = jdbcTemplate.getDataSource().getConnection();) {

            PreparedStatement statement = connection.prepareStatement(update);

            statement.setDouble(4, t.getTask_id());
            statement.setLong(1, t.getArg());
            statement.setString(2, t.getNameTask());
            statement.setArray(3, connection.createArrayOf("bigint", t.getArr()));
            statement.addBatch();

            int executeBatch = statement.executeUpdate();
            System.out.println("Изменено строк " + executeBatch);
            return executeBatch;

        } catch (SQLException e) {
            System.out.println("Error execute statement..." + e.getMessage());
            // e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int delete(int id) {

        String sql = String.format(delete,id);
        int executeBatch = jdbcTemplate.update(sql);
        System.out.println("Изменено строк " + executeBatch);
        return executeBatch;
    }

    private final static class TaskRowMapper implements RowMapper<TaskResult> {

        @Override
        public TaskResult mapRow(ResultSet rs, int i) throws SQLException {
            if (i < 0) {
                return null;
            }
            Long[] arr =  (Long[]) rs.getArray(5).getArray();

            TaskResult res = new TaskResult(rs.getLong(1),
                                            rs.getInt(2),
                                            rs.getLong(3),
                                            rs.getString(4),
                                            arr);
            return res;
        }
    }
}
