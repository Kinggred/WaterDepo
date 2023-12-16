package Services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Models.Kayak.Kayak;
import Models.Order.Rental;
import Repositories.KayakModelRepository;
import Repositories.KayakRepository;
import Repositories.OrderRepository;
import Repositories.RentalRepository;

@Service
public class ScheduleService {

    @Autowired RentalRepository rentalRepository;
    @Autowired OrderRepository orderRepository;
    @Autowired KayakRepository kayakRepository;
    @Autowired KayakModelRepository kayakModelRepository;

    public List<Kayak> getKayaksAvailableInTime(LocalDate startDate, LocalDate endDate){
        List<Kayak> allKayaks = kayakRepository.findAll();
        List<UUID> unavailabeKayakIds = new ArrayList<>();
        List<Kayak> availableKayaks = new ArrayList<>();
        List<Rental> rentalsBlocked = rentalRepository.findByStartDateAfterAndEndDateBefore(startDate, endDate);
        System.out.println(rentalsBlocked); // TODO: Loggers maybe
        for (Rental rental : rentalsBlocked) {
            unavailabeKayakIds.add(rental.getKayak().getId());
        }
        for (Kayak kayak : allKayaks) {
            if (unavailabeKayakIds.contains(kayak.getId()) == false) {
                availableKayaks.add(kayak);
            } 
        }
        return availableKayaks;
    }
}
