package ru.geekbrains.myshop.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.myshop.persistence.entities.Purchase;

import java.util.UUID;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {}
