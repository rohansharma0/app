package com.wipro.app.repository;

import com.wipro.app.model.Machine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MachineRepository extends JpaRepository<Machine , Long> {
}
