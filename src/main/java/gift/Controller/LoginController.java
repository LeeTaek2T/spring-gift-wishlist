package gift.Controller;

import gift.DTO.JwtToken;
import gift.DTO.LoginDto;
import gift.Service.LoginService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class LoginController {

  private final LoginService loginService;

  public LoginController(LoginService loginService) {
    this.loginService = loginService;
  }

  @PostMapping("/signup")
  public LoginDto UserSignUp(@Valid @RequestBody LoginDto userInfo) {
    return loginService.UserSignUp(userInfo);
  }

  @PostMapping("/login")
  public ResponseEntity<JwtToken> userLogin(
    @Valid @RequestBody LoginDto userInfo) {
    String email = userInfo.getEmail();
    String password = userInfo.getPassword();
    return loginService.UserLogin(email,password);
  }
}
