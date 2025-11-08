package com.KissTech.crm.repository;

import com.KissTech.crm.model.StudentManagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@EnableJpaRepositories
public interface StudentManagementRepository extends JpaRepository<StudentManagement , UUID> {
}
