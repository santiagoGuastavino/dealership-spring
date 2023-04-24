package smg.dealership.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
