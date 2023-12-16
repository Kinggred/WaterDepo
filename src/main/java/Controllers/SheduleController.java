package Controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
import Services.ScheduleService;

@Controller
@RequestMapping("/schedule")
public class SheduleController {
    @Autowired KayakMapper mapper; 
    @Autowired ScheduleService scheduleService; 
    
    @GetMapping
    public ResponseEntity<List<KayakDto>> getAvailableKayaks(@RequestParam UUID modelId, @RequestParam LocalDate dateStart, @RequestParam(required = false) LocalDate dateEnd) {
        System.out.println(modelId + " " + dateStart + " " + dateEnd); // TODO: Remove
        List<Kayak> availableKayaks = scheduleService.getKayaksAvailableInTime(dateStart, dateEnd);
        return ResponseEntity.ok(mapper.toDto(availableKayaks));
    } 

    @PostMapping("/rent")
    public ResponseEntity placeRentOrder(@AuthenticationPrincipal UserDetails userDetails, @RequestBody Order order) {
        return ResponseEntity.ok(order);
    }
}
