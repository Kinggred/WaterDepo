package Models.Kayak.DTO;

import java.util.UUID;


public class KayakDto {
  private UUID id;
  private KayakModelDto type;

  public KayakDto(UUID id, KayakModelDto type) {
    this.id = id;
    this.type = type;
  }

  public UUID getId() {
      return id;
  }
  public void setId(UUID id) {
      this.id = id;
  }
  public KayakModelDto getType() {
      return type;
  }
  public void setType(KayakModelDto type) {
      this.type = type;
  }
}

