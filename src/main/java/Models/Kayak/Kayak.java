package Models.Kayak;

import jakarta.persistence.*;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "Kayaks")
public class Kayak {
  @Id @GeneratedValue(strategy = GenerationType.UUID) private UUID id;

@JsonManagedReference
@ManyToOne
  @JoinColumn(name = "kayaks", nullable = false)
  private KayakModel type;

  public void setType(KayakModel type) {
	this.type = type;
}

@OneToMany(mappedBy = "kayak") private Set<Rental> rents;

  public UUID getId() {
      return id;
  }
  public Set<Rental> getRents() { return rents; }

  public KayakModel getType() { return type; }
}
