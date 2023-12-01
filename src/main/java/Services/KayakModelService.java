package Services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Models.Kayak.KayakModel;
import Repositories.KayakModelRepository;

@Service
public class KayakModelService {

    @Autowired private KayakModelRepository kayakModelRepository;

    public KayakModel createKayakModel(KayakModel model) {
        return kayakModelRepository.save(model);
    }

    public KayakModel getKayakModelById(UUID id) {
        return kayakModelRepository.getReferenceById(id);
    }
}
