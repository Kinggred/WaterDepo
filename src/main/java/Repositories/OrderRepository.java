package Repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Models.Kayak.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID>{}
