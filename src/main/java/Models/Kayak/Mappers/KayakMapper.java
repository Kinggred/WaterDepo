package Models.Kayak.Mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import Models.Kayak.Kayak;
import Models.Kayak.DTO.KayakDto;
import Models.Kayak.DTO.KayakModelDto;

@Component
public class KayakMapper {
    @Autowired KayakModelMapper mapper;

   public KayakDto toDto(Kayak kayak) {
    KayakModelDto model = mapper.toDto(kayak.getType());
    return new KayakDto(kayak.getId(), model);
   } 

    public Kayak fromDto(KayakDto kayakDto) {
        Kayak kayak = new Kayak();
        kayak.setId(kayakDto.getId());
        kayak.setType(mapper.fromDto(kayakDto.getType()));
        return kayak;
    }

    public List<KayakDto> toDto(List<Kayak> kayaks) {
        List<KayakDto> kayakDtos = new ArrayList<KayakDto>();
        for (Kayak kayak : kayaks) {
            kayakDtos.add(toDto(kayak));
        }
        return kayakDtos;
    }

    public List<Kayak> fromDto(List<KayakDto> kayakDtos) {
        List<Kayak> kayaks = new ArrayList<Kayak>();
        for (KayakDto kayakDto : kayakDtos) {
            kayaks.add(fromDto(kayakDto));
        }
        return kayaks;
    }
}
