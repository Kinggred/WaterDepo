package Models.Order.DTO;

import java.time.LocalDate;
import java.util.UUID;

public class placeAnRentalDTO {
    private UUID kayakId;
	private LocalDate startDate;
    private LocalDate endDate;
    public UUID getKayakId() {
        return kayakId;
    }
    public void setKayakId(UUID kayakId) {
        this.kayakId = kayakId;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    }
