package smg.dealership.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import smg.dealership.dto.request.CreateVehicleDTO;
import smg.dealership.dto.response.ResponseDTO;
import smg.dealership.dto.response.VehicleDTO;
import smg.dealership.dto.response.VehicleDetailsDTO;
import smg.dealership.entity.Vehicle;
import smg.dealership.exceptions.AlreadyExistsException;
import smg.dealership.exceptions.NotFoundException;
import smg.dealership.repository.VehicleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VehicleService {
    @Autowired
    VehicleRepository repository;

    public ResponseDTO<VehicleDTO> create(CreateVehicleDTO payload) {
        Optional<Vehicle> vehicleExists = repository.findOne(vehicle ->
            vehicle.getLicensePlate().equals(payload.getLicensePlate())
        );
        if (vehicleExists.isPresent()) {
            throw new AlreadyExistsException(String.format("The provided license plate (%s) has already been registered", payload.getLicensePlate()));
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

        VehicleDTO vehicle = new VehicleDTO(
            vehicleToSave.getId(),
            payload.getBrand(),
            payload.getModel(),
            payload.getManufacturingYear(),
            payload.getCurrency() + "$" + payload.getPrice()
        );

        ResponseDTO response = new ResponseDTO<>(201, HttpStatus.CREATED, vehicle, new ArrayList<>());
        return response;
    }

    public ResponseDTO<List<VehicleDTO>> listUsedVehicles() {
        ArrayList<Vehicle> filteredVehicleList = repository.listVehicles(vehicle ->
                Integer.parseInt(vehicle.getCountOfOwners()) > 0
        );
        if (filteredVehicleList.isEmpty()) {
            throw new NotFoundException("No used vehicles were found.");
        }

        List<VehicleDTO> vehicleList = filteredVehicleList
            .stream()
            .map(this::mapToVehicleResponseDTO)
            .toList();

        ResponseDTO response = new ResponseDTO<>(200, HttpStatus.OK, vehicleList, new ArrayList<>());
        return response;
    }

    public ResponseDTO<List<VehicleDTO>> listByManufacturingDate(int from, int to) {
        ArrayList<Vehicle> filteredVehicleList = repository.listVehicles(vehicle ->
            vehicle.getManufacturingYear() >= from && vehicle.getManufacturingYear() <= to
        );
        if (filteredVehicleList.isEmpty()) {
            throw new NotFoundException(String.format("No vehicles manufactured between %s and %d were found", from, to));
        }

        List<VehicleDTO> vehicleList = filteredVehicleList
            .stream()
            .map(this::mapToVehicleResponseDTO)
            .toList();

        ResponseDTO response = new ResponseDTO<>(200, HttpStatus.OK, vehicleList, new ArrayList<>());
        return response;
    }

    public ResponseDTO<VehicleDetailsDTO> getById(String id) {
        Optional<Vehicle> foundVehicle = repository.findOne(vehicle ->
            vehicle.getId().equals(id)
        );
        if (!foundVehicle.isPresent()) {
            throw new NotFoundException(String.format("The provided license id (%s) doesn't exist", id));
        }

        VehicleDetailsDTO vehicleDetails = mapToVehicleDetailsResponseDTO(foundVehicle.get());

        ResponseDTO response = new ResponseDTO<>(200, HttpStatus.OK, vehicleDetails, new ArrayList<>());
        return response;
    }

    private VehicleDTO mapToVehicleResponseDTO(Vehicle vehicle) {
        VehicleDTO vehicleDto = new VehicleDTO(
            vehicle.getId(),
            vehicle.getBrand(),
            vehicle.getModel(),
            vehicle.getManufacturingYear(),
            vehicle.getCurrency() + "$" + vehicle.getPrice()
        );
        return vehicleDto;
    }

    private VehicleDetailsDTO mapToVehicleDetailsResponseDTO(Vehicle vehicle) {
        VehicleDetailsDTO vehicleDetailsDto = new VehicleDetailsDTO(
            vehicle.getLicensePlate(),
            vehicle.getBrand(),
            vehicle.getModel(),
            vehicle.getManufacturingYear(),
            vehicle.getNumberOfKilometers(),
            vehicle.getDoors(),
            vehicle.getPrice(),
            vehicle.getServices(),
            vehicle.getCountOfOwners()
        );
        return vehicleDetailsDto;
    }
}
