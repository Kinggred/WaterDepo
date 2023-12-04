package Services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import Models.Kayak.Kayak;
import Models.Kayak.KayakModel;
import Repositories.KayakModelRepository;
import Repositories.KayakRepository;

@Service
public class KayakService {
     @Autowired
     private KayakRepository kayakRepository;
    
     @Autowired
     private KayakModelRepository kayakModelRepository;
    
     public Kayak createKayak(Kayak model) {
         return kayakRepository.save(model);
     }

     public List<Kayak> getAllKayaks() {
         return kayakRepository.findAll();
     };

     public Kayak getKayakById(UUID id) {
         return kayakRepository.getReferenceById(id);
     }

     public List<Kayak> GetKayaksByModelId(UUID id) {
         Kayak kayak = new Kayak();
         KayakModel kayakModel = kayakModelRepository.getReferenceById(id);
         kayak.setType(kayakModel);

         Example<Kayak> example = Example.of(kayak);
         List<Kayak> kayaksInDb = kayakRepository.findAll(example);
         return kayaksInDb;
     }
}
