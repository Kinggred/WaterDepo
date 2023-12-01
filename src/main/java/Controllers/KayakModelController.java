package Controllers;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import Models.Errors.ErrorRes;
import Models.Kayak.KayakModel;
import Services.KayakModelService;

@Controller
@RequestMapping("/administrative/models")
public class KayakModelController {

    @Autowired
    
    KayakModelService kayakModelService;

    @PostMapping
    public ResponseEntity createKayakModel(@RequestBody KayakModel kayakModel) {
        try {
            KayakModel kayakModelInDb = kayakModelService.createKayakModel(kayakModel);
            HashMap<String, String> response = new HashMap<>();
            response.put("info", "Succesfully created a new kayak type");
            // TODO: Put some data here
            response.put("name", "plpaceholder");
            return ResponseEntity.ok(response);
        } catch (DataIntegrityViolationException e) {
            ErrorRes errorRes = new ErrorRes(HttpStatus.BAD_REQUEST, "I mean, you tried");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorRes);
        }
    }
}
