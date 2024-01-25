package Models.Order.Mapper;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import Models.Order.Order;
import Models.Order.Rental;
import Models.Order.DTO.OrderDTO;
import Models.User.User;
import Repositories.RentalRepository;
import Repositories.UserRepository;

@Component
public class OrderMapper {
    @Autowired UserRepository userRepository;
    @Autowired RentalRepository rentalRepository;
    public OrderDTO toDto(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        Rental exampleRental = order.getRentals().iterator().next();
        List<UUID> rentals = new ArrayList<>();
        orderDTO.setId(order.getId());
        orderDTO.setUserId(order.getUser().getId());
        orderDTO.setStartDate(exampleRental.getStartDate());
        orderDTO.setEndDate(exampleRental.getEndDate());
        for (Rental rental: order.getRentals()) {
            rentals.add(rental.getId());
        }
        orderDTO.setRentals(rentals);
        return orderDTO;
    }

    public Order fromDto(OrderDTO orderDTO) {
        Order order = new Order();
        User user = userRepository.getReferenceById(orderDTO.getUserId());
        order.setId(orderDTO.getId());
        order.setUser(user);
        Set<Rental> rentals = new HashSet<>();
        for (UUID rentalId : orderDTO.getRentals()) {
            rentals.add(rentalRepository.getReferenceById(rentalId));
        }
        order.setRentals(rentals);
        return order;
    }

    public List<OrderDTO> toDto(List<Order> orders) {
        List<OrderDTO> orderDTOs = new ArrayList<>();
        for (Order order : orders) {
            orderDTOs.add(toDto(order));
        }
        return orderDTOs;
    }

    public List<Order> fromDto(List<OrderDTO> orderDTOs) {
        List<Order> orders = new ArrayList<>();
        for (OrderDTO orderDTO : orderDTOs) {
            orders.add(fromDto(orderDTO));
        }
        return orders;
    }
}
