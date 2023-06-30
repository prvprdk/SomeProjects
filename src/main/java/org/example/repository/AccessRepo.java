package org.example.repository;

import org.example.domain.Access;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessRepo extends JpaRepository <Access, Long> {
}
