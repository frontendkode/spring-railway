package com.KissTech.crm.repository;

import com.KissTech.crm.model.Attendance;
import org.checkerframework.checker.units.qual.UnknownUnits;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, UUID> {
}
