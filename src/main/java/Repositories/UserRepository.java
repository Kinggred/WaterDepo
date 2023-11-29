package Repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Models.User.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {}

