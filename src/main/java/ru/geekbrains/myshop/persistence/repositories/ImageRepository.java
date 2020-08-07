package ru.geekbrains.myshop.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.geekbrains.myshop.persistence.entities.Image;

import java.util.UUID;

public interface ImageRepository extends JpaRepository<Image, Long> {
    @Query(value = "SELECT image.name FROM image INNER JOIN product p ON image.id = p.image WHERE p.id = :id", nativeQuery = true)
    String obtainImageNameByProductId(@Param("id") Long id);

    @Query(value = "SELECT image.name FROM image WHERE id = :id", nativeQuery = true)
    String obtainImageNameById(@Param("id") Long id);
}