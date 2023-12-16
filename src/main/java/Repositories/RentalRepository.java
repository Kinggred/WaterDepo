package Repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Models.Order.Rental;


@Repository
public interface RentalRepository extends JpaRepository<Rental, UUID> {
    List<Rental> findByStartDateAfterAndEndDateBefore(LocalDate startDate, LocalDate endDate);
}
