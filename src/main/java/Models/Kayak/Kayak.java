package Models.Kayak;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import Models.Order.Rental;
import jakarta.persistence.*;
import java.util.Set;
import java.util.UUID;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
                  property = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "Kayaks")
public class Kayak {
  
  @Id @GeneratedValue(strategy = GenerationType.UUID) private UUID id;
  
  @ManyToOne
  @JoinColumn(name = "typeId", nullable = false)
  private KayakModel type;

  @OneToMany(mappedBy = "kayak") private Set<Rental> rents;

  public void setId(UUID id) { this.id = id; }

  public void setRents(Set<Rental> rents) { this.rents = rents; }


  public UUID getId() { return id; }

  public void setType(KayakModel type) { this.type = type; }

  public Set<Rental> getRents() { return rents; }

  public KayakModel getType() { return type; }
}
