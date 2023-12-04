package Controllers;

import Models.Kayak.Kayak;
import Models.Kayak.KayakModel;
import Services.KayakModelService;
import Services.KayakService;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/administrative/kayaks")
public class KayakController {

  @Autowired private KayakService kayakService;
  @Autowired private KayakModelService kayakModelService;

  @PostMapping
  public ResponseEntity CreateKayak(@RequestBody Kayak kayak) {
    try {
      KayakModel kayakModel =
          kayakModelService.getKayakModelById(kayak.getType().getId());
      Kayak kayakInDb = new Kayak();
      kayakInDb.setType(kayakModel);
      kayakService.createKayak(kayakInDb);
      HashMap<String, String> response = new HashMap<>();
      response.put("info", "Added new Kayak");
      return ResponseEntity.ok(response);
    } catch (Exception e) {
      return ResponseEntity.ok(e);
    }
  }

  @GetMapping
  public ResponseEntity getKayaks() {
    try {
      List<Kayak> kayaks = kayakService.getAllKayaks();
      return ResponseEntity.ok(kayaks);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }
}
