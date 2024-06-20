package com.wipro.app.controller;

import com.wipro.app.model.Machine;
import com.wipro.app.payload.ApiResponse;
import com.wipro.app.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MachineController {

    @Autowired
    private MachineService machineService;

    @PutMapping("/machine/{machineId}")
    public ResponseEntity<Machine> updateMachine(@RequestBody Machine machine , @PathVariable Long machineId){
        Machine updateMachine = this.machineService.updateMachineById(machine,machineId);
        return new ResponseEntity<>(updateMachine , HttpStatus.OK);
    }

    @DeleteMapping("/machine/{machineId}")
    public ResponseEntity<ApiResponse> deleteMachine(@PathVariable Long machineId){
        this.machineService.deleteMachineById(machineId);
        return new ResponseEntity<>(new ApiResponse("Machine deleted successfully" , true , 200) , HttpStatus.OK);
    }

    @GetMapping("/machine/{machineId}")
    public ResponseEntity<Machine> getMachine(@PathVariable Long machineId){
        return ResponseEntity.ok(this.machineService.getMachineById(machineId));
    }

    @GetMapping("/machines")
    public ResponseEntity<List<Machine>> getAllMachines(){
        return ResponseEntity.ok(this.machineService.getAllMachines());
    }
}
