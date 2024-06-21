package com.wipro.app.service;

import com.wipro.app.model.AuditLog;
import com.wipro.app.model.Machine;

import java.util.List;

public interface AuditLogService {
    List<AuditLog> getLogsByMachineId(Long machineId);

    AuditLog createLog(Machine machine , String username);
}
