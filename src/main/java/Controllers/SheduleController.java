package Controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.jaxb.SpringDataJaxb.OrderDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Models.Kayak.Kayak;
import Models.Kayak.DTO.KayakDto;
import Models.Kayak.Mappers.KayakMapper;
import Models.Order.Order;
import Models.Order.DTO.MonoDatePlaceAnOrderDTO;
import Models.Order.DTO.OrderDTO;
import Models.Order.Mapper.OrderMapper;
import Models.User.User;
import Services.ScheduleService;
import Services.UserService;

@Controller
@RequestMapping("/schedule")
public class SheduleController {
    @Autowired KayakMapper kayakMapper; 
    @Autowired OrderMapper orderMapper;
    @Autowired ScheduleService scheduleService; 
    @Autowired UserService userService;

    @GetMapping
    public ResponseEntity<List<KayakDto>> getAvailableKayaks(@RequestParam UUID modelId, @RequestParam LocalDate dateStart, @RequestParam(required = false) LocalDate dateEnd) {
        System.out.println(modelId + " " + dateStart + " " + dateEnd); // TODO: Remove
        List<Kayak> availableKayaks = scheduleService.getKayaksAvailableInTime(dateStart, dateEnd);
        return ResponseEntity.ok(kayakMapper.toDto(availableKayaks));
    } 

    @PostMapping("/rent")
    public ResponseEntity<OrderDTO> placeRentOrder(@RequestBody MonoDatePlaceAnOrderDTO order) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByEmail(authentication.getName());
        Order orderInDb = scheduleService.placeAnOrder(order, user.getId());
        return ResponseEntity.ok(orderMapper.toDto(orderInDb));
    }
}
