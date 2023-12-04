package Repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Models.Kayak.Rental;

@Repository
public interface RentalRepository extends JpaRepository<Rental, UUID> {}
