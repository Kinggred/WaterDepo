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
        List<UUID> unavailableKayakIds = new ArrayList<>();
        List<Kayak> availableKayaks = new ArrayList<>();
        List<Rental> rentalsBlocked = rentalRepository.findOverlappingRentals(startDate, endDate);
        for (Rental rental : rentalsBlocked) {
            unavailableKayakIds.add(rental.getKayak().getId());
        }
        for (Kayak kayak : allKayaks) {
            if (!unavailableKayakIds.contains(kayak.getId())) {
                availableKayaks.add(kayak);
            } 
        }
        return availableKayaks;
    }

    public Order placeAnOrder(MonoDatePlaceAnOrderDTO orderDTO, UUID userId) throws Exception {
        Order order = new Order();
        User user = userRepository.getReferenceById(userId);
        order.setUser(user);

        List<Kayak> availableKayaks = getKayaksAvailableInTime(orderDTO.getStartDate(), orderDTO.getEndDate());
        Set<UUID> availableKayakIds = new HashSet<>();
        for (Kayak kayak : availableKayaks) {
            availableKayakIds.add(kayak.getId());
        }

        if (!availableKayakIds.containsAll(new HashSet<>(orderDTO.getKayakIds()))) {
            throw new Exception();
        }

        Set<Rental> rentals = new HashSet<>();
        for (UUID kayakId: orderDTO.getKayakIds()) {
            Rental rental = new Rental();
            rental.setKayak(kayakRepository.getReferenceById(kayakId));
            rental.setStartDate(orderDTO.getStartDate());
            rental.setEndDate(orderDTO.getEndDate());
            rentals.add(rental);
        }
        rentalRepository.saveAll(rentals);

        for (Rental rental : rentals) {
            rental.setOrder(order);
        }
        order.setRentals(rentals);
        orderRepository.save(order);
        rentalRepository.saveAll(rentals);
        return order;
    }
}
