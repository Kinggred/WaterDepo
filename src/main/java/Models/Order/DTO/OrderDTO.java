package Models.Order.DTO;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class OrderDTO {
    private UUID id;
    private UUID userId;
	private List<UUID> rentals;
	private LocalDate startDate;
	private LocalDate endDate;

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
    public List<UUID> getRentals() {
        return rentals;
    }
    public void setRentals(List<UUID> rentals) {
        this.rentals = rentals;
    }
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public UUID getUserId() {
        return userId;
    }
    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
