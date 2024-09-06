package com.vakya.bakendprojet.repository;

import com.vakya.bakendprojet.models.Document;
import com.vakya.bakendprojet.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Long> {
    List<Document> findByUser(User user);
    Document findById(long id);
}