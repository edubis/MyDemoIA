package com.example.bookstore.repositories;

import com.example.bookstore.models.GraphicCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GraphicCardRepository extends JpaRepository<GraphicCard, UUID> {
}
