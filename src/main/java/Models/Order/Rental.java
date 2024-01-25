package Models.Order;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.UUID;

import Models.Kayak.Kayak;

@Entity
@Table
public class Rental {
  @Id @GeneratedValue(strategy = GenerationType.UUID) private UUID id;


@ManyToOne @JoinColumn(name = "kayakId") private Kayak kayak;


@ManyToOne @JoinColumn(name = "orderId") private Order order;

private LocalDate startDate;

private LocalDate endDate;

public LocalDate getEndDate() {
    return endDate;
}
public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
}

public LocalDate getStartDate() {
	return startDate;
}
public void setStartDate(LocalDate startDate) {
	this.startDate = startDate;
}
public UUID getId() {
    return id;
}
public void setId(UUID id) {
    this.id = id;
}
public Kayak getKayak() {
    return kayak;
}
public void setKayak(Kayak kayak) {
    this.kayak = kayak;
}
public Order getOrder() {
    return order;
}
public void setOrder(Order order) {
    this.order = order;
}
}
