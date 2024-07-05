package gift.Repository;

import gift.DTO.UserDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

  private final JdbcTemplate jdbcTemplate;

  public UserDao(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public void UserSignUp(UserDto userInfo) {
    var sql = "INSERT INTO USERS(email,pw) VALUES(?,?)";
    jdbcTemplate.update(sql, userInfo.getEmail(), userInfo.getPw());
  }

  public UserDto UserLogin(String email, String pw) {
    var sql = "SELECT * FROM USERS WHERE email = ?";
    return jdbcTemplate.queryForObject(sql, new String[]{email}, userRowMapper());
  }


  private RowMapper<UserDto> userRowMapper() {
    return (rs, rowNum) -> new UserDto(
      rs.getString("email"),
      rs.getString("pw")
    );
  }

}