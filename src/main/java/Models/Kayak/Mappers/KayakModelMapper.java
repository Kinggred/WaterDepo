package Models.Kayak.Mappers;

import Models.Kayak.DTO.KayakModelDto;
import Models.Kayak.KayakModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class KayakModelMapper {
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

  public List<KayakModelDto> toDto(List<KayakModel> kayakModels) {
    List<KayakModelDto> kayakModelDtos = new ArrayList<KayakModelDto>();
    for (KayakModel model : kayakModels) {
      kayakModelDtos.add(toDto(model));
    }
    return kayakModelDtos;
  }

  public List<KayakModel> fromDto(List<KayakModelDto> kayakModelDtos) {
    List<KayakModel> kayakModels = new ArrayList<KayakModel>();
    for (KayakModelDto model : kayakModelDtos) {
      kayakModels.add(fromDto(model));
    }
    return kayakModels;
  }
}
