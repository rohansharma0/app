package com.wipro.app.service.impl;

import com.wipro.app.model.AuditLog;
import com.wipro.app.model.Machine;
import com.wipro.app.repository.AuditLogRepository;
import com.wipro.app.repository.MachineRepository;
import com.wipro.app.service.AuditLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class AuditLogServiceImpl implements AuditLogService {

    @Autowired
    private AuditLogRepository auditLogRepository;

    @Autowired
    private MachineRepository machineRepository;

    @Override
    public List<AuditLog> getLogsByMachineId(Long machineId) {
        return this.auditLogRepository.findByMachineId(machineId);
    }

    @Override
    public AuditLog createLog(Machine machine, String username) {
        AuditLog log = new AuditLog();
        log.setMachine(machine);
        log.setSerialNumber(machine.getSerialNumber());
        log.setDeviceId(machine.getDeviceId());
        log.setMachineType(machine.getMachineType());
        log.setSimNo(machine.getSimNo());
        log.setCountry(machine.getCountry());
        log.setCity(machine.getCity());
        log.setChangedAt(new Timestamp(System.currentTimeMillis()));
        log.setChangedBy(username);
        return this.auditLogRepository.save(log);
    }
}
