package Services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Models.Kayak.Kayak;
import Models.Order.Order;
import Models.Order.Rental;
import Models.Order.DTO.MonoDatePlaceAnOrderDTO;
import Models.Order.DTO.placeAnOrderDTO;
import Models.Order.DTO.placeAnRentalDTO;
import Models.User.User;
import Repositories.KayakModelRepository;
import Repositories.KayakRepository;
import Repositories.OrderRepository;
import Repositories.RentalRepository;
import Repositories.UserRepository;

@Service
public class ScheduleService {

    @Autowired RentalRepository rentalRepository;
    @Autowired OrderRepository orderRepository;
    @Autowired KayakRepository kayakRepository;
    @Autowired KayakModelRepository kayakModelRepository;
    @Autowired UserRepository userRepository;

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

    public Order placeAnOrder(MonoDatePlaceAnOrderDTO orderDTO, UUID userId) {
        Order order = new Order();
        User user = userRepository.getReferenceById(userId);
        order.setUser(user);

        Set<Rental> rentals = new HashSet<>();
        for (UUID kayakId: orderDTO.getKayakIds()) {
            Rental rental = new Rental();
            rental.setKayak(kayakRepository.getReferenceById(kayakId));
            rental.setStartDate(orderDTO.getStartDate());
            rental.setEndDate(orderDTO.getEndDate());
            rental.setOrder(order);
            rentals.add(rental);
            rentalRepository.save(rental);
        }

        order.setRentals(rentals);
        return orderRepository.save(order);
    }
}
