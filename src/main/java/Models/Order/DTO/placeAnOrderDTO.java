package Models.Order.DTO;

import java.util.List;

public class placeAnOrderDTO {
    private List<placeAnRentalDTO> rentalDTOs;

    public List<placeAnRentalDTO> getRentalDTOs() {
        return rentalDTOs;
    }

    public void setRentalDTOs(List<placeAnRentalDTO> placeAnRentalDTO) {
        this.rentalDTOs = placeAnRentalDTO;
    }
} 
