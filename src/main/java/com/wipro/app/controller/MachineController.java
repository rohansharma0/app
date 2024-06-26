package com.wipro.app.controller;

import com.wipro.app.model.Machine;
import com.wipro.app.payload.ApiResponse;
import com.wipro.app.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MachineController {

    @Autowired
    private MachineService machineService;

    @PostMapping("/machine")
    public ResponseEntity<Machine> addMachine(@RequestBody Machine machine , Principal principal){
        return ResponseEntity.ok(this.machineService.addMachine(machine , principal.getName()));
    }

    @PutMapping("/machine/{machineId}")
    public ResponseEntity<Machine> updateMachine(@RequestBody Machine machine , @PathVariable Long machineId , Principal principal){
        Machine updateMachine = this.machineService.updateMachineById(machine,machineId,principal.getName());
        return new ResponseEntity<>(updateMachine , HttpStatus.OK);
    }

    @DeleteMapping("/machine/{machineId}")
    public ResponseEntity<ApiResponse> deleteMachine(@PathVariable Long machineId ,Principal principal){
        this.machineService.deleteMachineById(machineId,principal.getName());
        return new ResponseEntity<>(new ApiResponse("Machine deleted successfully" , true , 200) , HttpStatus.OK);
    }

    @GetMapping("/machine/{machineId}")
    public ResponseEntity<Machine> getMachine(@PathVariable Long machineId){
        return ResponseEntity.ok(this.machineService.getMachineById(machineId));
    }

    @GetMapping("/machine/all")
    public ResponseEntity<List<Machine>> getAllMachines(){
        return ResponseEntity.ok(this.machineService.getAllMachines());
    }
}
