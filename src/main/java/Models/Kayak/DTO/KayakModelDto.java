package Models.Kayak.DTO;

import java.util.Map;
import java.util.UUID;

import jakarta.persistence.Convert;
import Models.Kayak.HashMapConverter;


public class KayakModelDto {
  private UUID id;
  
  @Convert(converter = HashMapConverter.class)
  private Map<String, Object> info;

  public KayakModelDto(UUID id, Map<String, Object> info) {
      this.id = id;
      this.info = info;
  }

public Map<String, Object> getInfo() {
	return info;
}
public void setInfo(Map<String, Object> info) {
	this.info = info;
}
public UUID getId() {
    return id;
}
}
