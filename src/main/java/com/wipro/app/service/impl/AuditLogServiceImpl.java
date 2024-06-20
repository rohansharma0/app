package com.wipro.app.service.impl;

import com.wipro.app.exception.ResourceNotFoundException;
import com.wipro.app.model.AuditLog;
import com.wipro.app.model.Machine;
import com.wipro.app.repository.AuditLogRepository;
import com.wipro.app.repository.MachineRepository;
import com.wipro.app.service.AuditLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditLogServiceImpl implements AuditLogService {

    @Autowired
    private AuditLogRepository auditLogRepository;

    @Autowired
    private MachineRepository machineRepository;

    @Override
    public List<AuditLog> getLogsByMachineId(Long machineId) {
        Machine machine =  this.machineRepository.findById(machineId).orElseThrow(() -> new ResourceNotFoundException("Machine" , "machineId" , machineId.toString()));
        return this.auditLogRepository.findByMachine(machine).orElseThrow(() -> new ResourceNotFoundException("Machine" , "machineId" , machineId.toString()));
    }

    @Override
    public AuditLog createLog(AuditLog auditLog) {
        return this.auditLogRepository.save(auditLog);
    }
}
