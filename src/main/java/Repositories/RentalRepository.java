package Repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import Models.Order.Rental;


@Repository
public interface RentalRepository extends JpaRepository<Rental, UUID> {
    @Query("SELECT r FROM Rental r WHERE " +
            "(r.startDate <= ?2 AND r.endDate >= ?1) OR " +
            "(r.startDate >= ?1 AND r.endDate <= ?2) OR " +
            "(r.startDate < ?2 AND r.endDate > ?1)")
    List<Rental> findOverlappingRentals(LocalDate startDate, LocalDate endDate);

}
