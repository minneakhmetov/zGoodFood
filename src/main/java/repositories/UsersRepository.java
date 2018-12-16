package repositories;

import models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersRepository {
    private static final String SQL_INSERT = "INSERT INTO users_table (name, surname, email, hash_password) values (?,?,?,?)";
    private static final String SQL_SELECT = "SELECT * from users_table where id = ?";
    private static final String SQL_SELECT_BY_EMAIL = "SELECT * from users_table where email = ?";

    JdbcTemplate jdbcTemplate;

    public UsersRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    RowMapper<User> rowMapper = new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            return User.builder()
                    .name(resultSet.getString("name"))
                    .email(resultSet.getString("email"))
                    .surname(resultSet.getString("surname"))
                    .hashPassword(resultSet.getString("hash_password"))
                    .id(resultSet.getLong("id"))
                    .build();
        }
    };

    public Long save(User user){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps =
                            connection.prepareStatement(SQL_INSERT, new String[] {"id"});
                    ps.setString(1, user.getName());
                    ps.setString(2, user.getSurname());
                    ps.setString(3, user.getEmail());
                    ps.setString(4, user.getHashPassword());
                    return ps;
                }, keyHolder);

        return keyHolder.getKey().longValue();

        //jdbcTemplate.update(SQL_INSERT, user.getName(), user.getSurname(), user.getEmail(), user.getHashPassword());
    }
    public User read(Long id){
        return jdbcTemplate.queryForObject(SQL_SELECT, rowMapper, id);
    }
    public User read(String email){
        return jdbcTemplate.queryForObject(SQL_SELECT_BY_EMAIL, rowMapper, email);
    }



}