package Models.Kayak;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@JsonIdentityReference(alwaysAsId = true)
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "kayak_type")
public class KayakModel {

  @Id @GeneratedValue(strategy = GenerationType.UUID) private UUID id;

  @OneToMany(mappedBy = "type") private Set<Kayak> kayaks;

  @Column(unique = true, nullable = false) private String name;

  // JSON Field
  @Convert(converter = HashMapConverter.class) private Map<String, Object> info;

  public UUID getId() { return id; }

  public void setId(UUID id) { this.id = id; }

  public String getName() { return name; }

  public void setName(String name) { this.name = name; }

  public Set<Kayak> getKayaks() { return kayaks; }

  public void setKayaks(Set<Kayak> kayaks) { this.kayaks = kayaks; }

  public Map<String, Object> getInfo() { return info; }

  public void setInfo(Map<String, Object> info) { this.info = info; }
}
