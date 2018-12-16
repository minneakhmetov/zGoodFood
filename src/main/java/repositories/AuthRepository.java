package repositories;

import models.Auth;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthRepository {

    private static final String SQL_INSERT = "INSERT INTO auth_table (user_id, cookie_value) values (?,?)";
    private static final String SQL_SELECT = "SELECT * FROM auth_table WHERE user_id = ?";
    private static final String SQL_DELETE = "DELETE FROM auth_table WHERE user_id = ?";
    private static final String SQL_DELETE_BY_COOKIE = "DELETE FROM auth_table WHERE cookie_value = ?";
    private static final String SQL_SELECT_BY_COOKIE = "SELECT * FROM auth_table WHERE cookie_value = ?";

    JdbcTemplate jdbcTemplate;

    public AuthRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    RowMapper<Auth> rowMapper = new RowMapper<Auth>() {
        @Override
        public Auth mapRow(ResultSet resultSet, int i) throws SQLException {
            return Auth.builder()
                    .userId(resultSet.getLong("user_id"))
                    .cookieValue(resultSet.getString("cookie_value"))
                    .build();
        }
    };

    public Auth read(Long id){
        return jdbcTemplate.queryForObject(SQL_SELECT, rowMapper, id);
    }

    public void save(Auth auth){
        jdbcTemplate.update(SQL_INSERT, auth.getUserId(), auth.getCookieValue());
    }

    public void delete(Long id){
        jdbcTemplate.update(SQL_DELETE, id);
    }
    public void delete(String cookie){
        jdbcTemplate.update(SQL_DELETE_BY_COOKIE, cookie);
    }

    public Auth read(String cookie){
        return jdbcTemplate.queryForObject(SQL_SELECT_BY_COOKIE, rowMapper, cookie);
    }

}