package Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Models.Kayak.Kayak;
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
}
