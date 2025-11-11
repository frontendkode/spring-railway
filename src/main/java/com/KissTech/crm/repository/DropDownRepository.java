package com.KissTech.crm.repository;

import com.KissTech.crm.model.DropDown;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
@EnableJpaRepositories
public interface DropDownRepository extends JpaRepository<DropDown, UUID> {
}
