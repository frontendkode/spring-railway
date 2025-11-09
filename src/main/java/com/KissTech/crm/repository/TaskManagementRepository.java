package com.KissTech.crm.repository;

import com.KissTech.crm.model.TaskManagement;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@EnableJpaRepositories
public interface TaskManagementRepository extends JpaRepository<TaskManagement, UUID> {
    List<TaskManagement> findByStaffManagementId(UUID id);
}
