package Models.User;

import jakarta.persistence.*;

import java.util.Set;
import java.util.UUID;

import Models.Order.Order;


@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private String name;

  @Column(unique = true)
  private String email;

  private String password;

  @OneToMany(mappedBy = "user")
  private Set<Order> orders;


  public UUID getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    // TODO: Password hashing
    this.password = password;
  }
}
