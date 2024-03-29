package Controllers;

import Models.Login.LoginReq;
import Models.Login.LoginRes;
import Models.User.User;
import Services.UserService;
import WaterDepo.auth.JwtUtil;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/rest/auth")
public class AuthController {

  @Autowired private JwtUtil jwtUtil;

  private final UserService userService;
  private final AuthenticationManager authenticationManager;

  public AuthController(AuthenticationManager authenticationManager,
                        UserService userService) {
    this.authenticationManager = authenticationManager;
    this.userService = userService;
  }

  @PostMapping("/register")
  public ResponseEntity<HashMap<String, String>>
  createUser(@RequestBody User user) {
    try {
      User userInDb = userService.createUser(user);
      HashMap<String, String> response = new HashMap<>();
      response.put("info", "User created");
      response.put("email", userInDb.getEmail());
      return ResponseEntity.ok(response);
    } catch (DataIntegrityViolationException e) {
      return ResponseEntity.badRequest().build();
    }
  }

  @ResponseBody
  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public ResponseEntity<LoginRes> login(@RequestBody LoginReq loginReq) {
    try {
      Authentication authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(loginReq.getEmail(),
                                                  loginReq.getPassword()));
      String email = authentication.getName();
      User user = userService.getUserByEmail(email);
      String token = jwtUtil.createToken(user);
      LoginRes loginRes = new LoginRes(user.getId(), email, token);
      return ResponseEntity.ok(loginRes);
    } catch (BadCredentialsException e) {
      // TODO: This is useless now.
      return ResponseEntity.badRequest().build();
    } catch (Exception e) {
      return ResponseEntity.badRequest().build();
    }
  }
}
