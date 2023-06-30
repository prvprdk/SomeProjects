package org.example.repository;

import org.example.domain.Site;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SiteRepo extends JpaRepository <Site, Long> {

}
