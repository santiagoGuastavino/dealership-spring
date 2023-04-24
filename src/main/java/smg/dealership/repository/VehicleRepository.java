package smg.dealership.repository;

import org.springframework.stereotype.Repository;
import smg.dealership.entity.Vehicle;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public class VehicleRepository {
    ArrayList<Vehicle> vehicleList = new ArrayList<>();

    public void insertOne(Vehicle vehicle) {
        vehicleList.add(vehicle);
    }

    public Optional<Vehicle> findOneByLicensePlate(String licensePlate) {
        return vehicleList
            .stream()
            .filter(vehicle -> vehicle.getLicensePlate().equalsIgnoreCase(licensePlate))
            .findFirst();
    }
}
