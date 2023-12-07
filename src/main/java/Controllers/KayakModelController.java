package Controllers;

import Models.Kayak.DTO.KayakModelDto;
import Models.Kayak.KayakModel;
import Models.Kayak.Mappers.KayakModelMapper;
import Services.KayakModelService;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/administrative/models")
public class KayakModelController {

    @Autowired
    KayakModelService kayakModelService;

    @Autowired
    KayakModelMapper mapper;

    @PostMapping
    public ResponseEntity<KayakModelDto> createKayakModel(@RequestBody KayakModel kayakModel) {
        try {
            KayakModel kayakModelInDb = kayakModelService.createKayakModel(kayakModel);
            return ResponseEntity.ok(mapper.toDto(kayakModelInDb));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().build();
        }
      }

  @GetMapping
  public ResponseEntity<List<KayakModelDto>> getAvailableKayakModels() {
    try {
      List<KayakModel> models = kayakModelService.getAllKayakModels();
      return ResponseEntity.ok(mapper.toDto(models));
    } catch (Exception e) {
      return ResponseEntity.badRequest().build();
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<KayakModelDto>
  getSingleKayakModel(@PathVariable UUID id) {
    try {
      KayakModel model = kayakModelService.getKayakModelById(id);
      return ResponseEntity.ok(mapper.toDto(model));
    } catch (Exception e) {
      return ResponseEntity.badRequest().build();
    }
  }
}
