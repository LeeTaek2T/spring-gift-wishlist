package gift.DTO;

public class LoginDto {

  private String username;
  private String pw;

  public LoginDto(String username, String pw) {
    this.username = username;
    this.pw = pw;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getUsername() {
    return this.username;
  }

  public void setPw(String pw) {
    this.pw = pw;
  }

  public String getPw() {
    return this.pw;
  }

}