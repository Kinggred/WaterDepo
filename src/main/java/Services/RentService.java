package Services;

import Models.Kayak.Kayak;
import Models.Order.Rental;
import Repositories.RentalRepository;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class RentService {

  @Autowired RentalRepository rentalRepository;

  public Rental addNewRent(Rental service) {
    return rentalRepository.save(service);
  }

  public Rental getRentById(UUID id) {
    return rentalRepository.getReferenceById(id);
  }

  public List<Rental> getRentsByKayakId(UUID id) {
      Rental rent = new Rental();
      Kayak kayak = new Kayak();
      kayak.setId(id);
      rent.setKayak(kayak);

      Example<Rental> example = Example.of(rent);
      List<Rental> rentals = rentalRepository.findAll(example);
      return rentals;
  }
}
