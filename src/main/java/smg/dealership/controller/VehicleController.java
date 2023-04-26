package smg.dealership.controller;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smg.dealership.dto.request.CreateVehicleDTO;
import smg.dealership.dto.response.ResponseDTO;
import smg.dealership.service.VehicleService;
import org.springframework.http.HttpStatus;

import java.util.UUID;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {
    @Autowired
    VehicleService service;

    @PostMapping()
    public ResponseEntity<ResponseDTO> create(@RequestBody() CreateVehicleDTO payload) {
        return new ResponseEntity<>(service.create(payload), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<ResponseDTO> listUsedVehicles() {
        return new ResponseEntity<>(service.listUsedVehicles(), HttpStatus.OK);
    }

    @GetMapping("/dates")
    public ResponseEntity<ResponseDTO> listByManufacturingDate(@RequestParam int from, @RequestParam int to) {
        return new ResponseEntity<>(service.listByManufacturingDate(from, to), HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getById(@PathVariable String id) {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }
}
