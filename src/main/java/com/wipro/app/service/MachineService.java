package com.wipro.app.service;

import com.wipro.app.model.Machine;

import java.util.List;

public interface MachineService {

    List<Machine> getAllMachines();

    Machine getMachineById(Long machineId);

    Machine updateMachineById(Machine machine,Long machineId);

    void deleteMachineById(Long machineId);

    Machine addMachine(Machine machine);
}
