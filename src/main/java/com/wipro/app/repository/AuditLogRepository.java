package com.wipro.app.repository;

import com.wipro.app.model.AuditLog;
import com.wipro.app.model.Machine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long>{
    Optional<List<AuditLog>> findByMachine(Machine machine);
}
