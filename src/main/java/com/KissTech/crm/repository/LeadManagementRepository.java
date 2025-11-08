package com.KissTech.crm.repository;

import com.KissTech.crm.model.LeadManagement;
import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
@EnableJpaRepositories
public interface LeadManagementRepository extends JpaRepository<LeadManagement, UUID> {
}
