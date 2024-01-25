package Models.Login;

import java.util.UUID;

public class LoginRes {

    private UUID id;
    private String email;
    private String token;

    public LoginRes(UUID id, String email, String token) {
        this.id = id;
        this.email = email;
        this.token = token;
    }

  public UUID getId() {
      return id;
  }

    public void setId(UUID id) {
        this.id = id;
    }

  public String getEmail() {
      return email;
  }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
