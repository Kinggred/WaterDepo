package Models.Kayak;

import jakarta.persistence.*;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import Models.Kayak.DTO.KayakModelDto;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "Kayaks")
public class Kayak {
  @Id @GeneratedValue(strategy = GenerationType.UUID) private UUID id;

@ManyToOne
  @JoinColumn(name = "model_id", nullable = false)
  private KayakModelDto type;

  public void setType(KayakModel type) {
    KayakModelDto kDto = new KayakModelDto();
    kDto.setId(type.getId());
	this.type = kDto;
}

@OneToMany(mappedBy = "kayak") private Set<Rental> rents;

  public UUID getId() {
      return id;
  }
  public Set<Rental> getRents() { return rents; }

  public UUID getType() { 
      UUID id = type.getId();
      return id; }
}
