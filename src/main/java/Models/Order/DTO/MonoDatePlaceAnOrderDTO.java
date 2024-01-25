package Models.Order.DTO;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class MonoDatePlaceAnOrderDTO {
    private LocalDate startDate;
    private LocalDate endDate;
	private List<UUID> kayakIds;
    
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
    public List<UUID> getKayakIds() {
        return kayakIds;
    }
    public void setKayakIds(List<UUID> kayakIds) {
        this.kayakIds = kayakIds;
    }
}
