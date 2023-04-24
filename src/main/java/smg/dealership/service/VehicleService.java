package smg.dealership.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import smg.dealership.dto.request.CreateVehicleDTO;
import smg.dealership.dto.response.ResponseDTO;
import smg.dealership.entity.Vehicle;
import smg.dealership.repository.VehicleRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class VehicleService {
    @Autowired
    VehicleRepository repository;

    public ResponseDTO create(CreateVehicleDTO payload) {
        Optional<Vehicle> vehicleExists = repository.findOneByLicensePlate(payload.getLicensePlate());
        if (vehicleExists.isPresent()) {
            throw new Error();
        }

        String id = UUID.randomUUID().toString();
        Vehicle vehicleToSave = new Vehicle(
            id,
            payload.getLicensePlate(),
            payload.getBrand(),
            payload.getModel(),
            payload.getManufacturingYear(),
            payload.getNumberOfKilometers(),
            payload.getDoors(),
            payload.getPrice(),
            payload.getCurrency(),
            payload.getServices(),
            payload.getCountOfOwners()
        );
        repository.insertOne(vehicleToSave);
        ResponseDTO response = new ResponseDTO(201, HttpStatus.CREATED);
        return response;
    }
}
