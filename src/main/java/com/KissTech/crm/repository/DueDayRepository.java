package com.KissTech.crm.repository;

import com.KissTech.crm.model.DueDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@Repository
public interface DueDayRepository extends JpaRepository<DueDay , UUID> {
}
