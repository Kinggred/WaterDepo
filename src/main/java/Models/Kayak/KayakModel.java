package Models.Kayak;

import jakarta.persistence.*;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "kayak_type")
public class KayakModel {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(unique = true)
  private String name;

  @Convert(converter = HashMapConverter.class)
  private Map<String, Object> info;
}
