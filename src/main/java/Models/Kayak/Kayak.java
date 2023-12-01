package Models.Kayak;

import jakarta.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "Kayaks")
public class Kayak {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @ManyToOne
  @JoinColumn(name = "id")
  private UUID typeId;

  private Set<KayakModel> type;
}
