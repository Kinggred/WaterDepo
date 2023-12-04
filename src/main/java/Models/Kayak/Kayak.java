package Models.Kayak;

import jakarta.persistence.*;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "Kayaks")
public class Kayak {
  @Id @GeneratedValue(strategy = GenerationType.UUID) private UUID id;

  @ManyToOne
  @JoinColumn(name = "kayaks", nullable = false)
  private KayakModel type;

  @OneToMany(mappedBy = "kayak") private Set<Rental> rents;
}
