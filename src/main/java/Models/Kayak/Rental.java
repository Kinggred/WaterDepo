package Models.Kayak;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.UUID;

@Entity
@Table
public class Rental {
  @Id @GeneratedValue(strategy = GenerationType.UUID) private UUID id;

  @ManyToOne @JoinColumn(name = "rents") private Kayak kayak;

  @ManyToOne @JoinColumn(name = "rentals") private Order order;
}
