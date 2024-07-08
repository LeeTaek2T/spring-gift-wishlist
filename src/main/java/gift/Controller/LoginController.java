package gift.Controller;

import gift.DTO.JwtToken;
import gift.DTO.UserDto;
import gift.Service.JwtService;
import gift.Service.LoginService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class LoginController {

  private final LoginService loginService;
  private final JwtService jwtService;

  public LoginController(LoginService loginService, JwtService jwtService) {
    this.loginService = loginService;
    this.jwtService = jwtService;
  }

  @PostMapping("/signup")
  public UserDto UserSignUp(@Valid @RequestBody UserDto userInfo) {
    return loginService.UserSignUp(userInfo);
  }

  @PostMapping("/login")
  public ResponseEntity<JwtToken> userLogin(
    @Valid @RequestBody UserDto userInfo) {
    String email = userInfo.getEmail();
    String password = userInfo.getPassword();

    UserDto userDto = loginService.UserLogin(userInfo);

    if (userDto == null) {
      return ResponseEntity.notFound().build();
    }
    if (email.equals(userDto.getEmail()) && password.equals(userDto.getPassword())) {
      JwtToken jwtToken = jwtService.createAccessToken(userDto);
      if (jwtService.isValidToken(jwtToken)) {
        return ResponseEntity.ok(jwtToken);
      }
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
    return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
  }
}
