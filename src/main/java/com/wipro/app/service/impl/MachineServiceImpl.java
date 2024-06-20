package com.wipro.app.service.impl;

import com.wipro.app.exception.ResourceNotFoundException;
import com.wipro.app.model.AuditLog;
import com.wipro.app.model.Machine;
import com.wipro.app.repository.MachineRepository;
import com.wipro.app.service.AuditLogService;
import com.wipro.app.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class MachineServiceImpl implements MachineService {

    @Autowired
    private MachineRepository machineRepository;

    @Autowired
    private AuditLogService auditLogService;

    @Override
    public List<Machine> getAllMachines() {
        return this.machineRepository.findAll();
    }

    @Override
    public Machine getMachineById(Long machineId) {
        return this.machineRepository.findById(machineId).orElseThrow(() -> new ResourceNotFoundException("Machine" , "machineId" , machineId.toString()));
    }

    @Override
    public Machine updateMachineById(Machine machine, Long id) {
        Machine savedMachine = this.machineRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Machine" , "machineId" , id.toString()));
        if(null!=machine.getMachineType()) savedMachine.setMachineType(machine.getMachineType());
        if(null!=machine.getCity())   savedMachine.setCity(machine.getCity());
        if(null!=machine.getCountry())  savedMachine.setCountry(machine.getCountry());
        if(null!=machine.getDeviceId()) savedMachine.setDeviceId(machine.getDeviceId());
        if(null!=machine.getSerialNumber()) savedMachine.setSerialNumber(machine.getSerialNumber());
        if(null!=machine.getSimNo()) savedMachine.setSimNo(machine.getSimNo());
        //log
        return this.machineRepository.save(savedMachine);
    }

    @Override
    public void deleteMachineById(Long machineId) {
        Machine machine = this.machineRepository.findById(machineId).orElseThrow(() -> new ResourceNotFoundException("Machine" , "machineId" , machineId.toString()));
        this.machineRepository.delete(machine);
    }

    @Override
    public Machine addMachine(Machine machine) {
        AuditLog log = new AuditLog();
        log.set
        log.setUpdatedDt(String.valueOf(new Date(System.currentTimeMillis())));
        this.auditLogService.createLog(log);
        return this.machineRepository.save(machine);
    }
}
