package Models.Kayak;

import java.util.UUID;

import Models.User.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
  @Id @GeneratedValue(strategy = GenerationType.UUID) private UUID id;

  @ManyToOne @JoinColumn(name = "orders", nullable = false) private User user;

  @OneToMany(mappedBy = "orders") private Rental rents;
}
