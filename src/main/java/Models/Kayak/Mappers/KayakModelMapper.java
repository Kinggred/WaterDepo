package Models.Kayak.Mappers;

import Models.Kayak.DTO.KayakModelDto;
import Models.Kayak.KayakModel;

import java.util.Map;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
class KayakModelMapper {
  public KayakModelDto toDto(KayakModel kayakModel) {
    UUID id = kayakModel.getId();
    Map<String, Object> info = kayakModel.getInfo();
    return new KayakModelDto(id, info);
  }
public KayakModel fromDto(KayakModelDto kayakModelDto) {
    KayakModel kayakModel = new KayakModel();
    kayakModel.setId(kayakModelDto.getId());
    kayakModel.setInfo(kayakModelDto.getInfo());
    return kayakModel;
  }
}
