package com.KissTech.crm.repository;

import com.KissTech.crm.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SessionRepository extends JpaRepository<Session, UUID> {
//    void deleteByUsername(String username);

    Session findByUserId(UUID userId);
}
