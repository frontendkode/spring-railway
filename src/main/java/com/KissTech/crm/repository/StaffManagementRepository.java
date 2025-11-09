package com.KissTech.crm.repository;

import com.KissTech.crm.model.StaffManagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@EnableJpaRepositories
@Repository
public interface StaffManagementRepository extends JpaRepository<StaffManagement, UUID> {
}
