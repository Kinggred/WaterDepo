package Models.Order;

import Models.User.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "orders")
public class Order {
  @Id @GeneratedValue(strategy = GenerationType.UUID) private UUID id;

  @ManyToOne @JoinColumn(name = "userId", nullable = false) private User user;

  @OneToMany(mappedBy = "order") private Set<Rental> rentals;

  public UUID getId() {
      return id;
  }

  public void setId(UUID id) {
      this.id = id;
  }

  public void setUser(User user) { this.user = user; }

  public User getUser() { return user; }

  public Set<Rental> getRentals() { return rentals; }

  public void setRentals(Set<Rental> rentals) {
      this.rentals = rentals;
  }
}
