package com.wipro.app.service;

import com.wipro.app.model.AuditLog;

import java.util.List;

public interface AuditLogService {
    List<AuditLog> getLogsByMachineId(Long machineId);

    AuditLog createLog(AuditLog auditLog);
}
