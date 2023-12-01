package Repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Models.Kayak.KayakModel;


@Repository
public interface KayakModelRepository extends JpaRepository<KayakModel, UUID> {}
