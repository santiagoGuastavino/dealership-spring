package smg.dealership.repository;

import org.springframework.stereotype.Repository;
import smg.dealership.entity.Vehicle;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public class VehicleRepository {
    ArrayList<Vehicle> vehicleList = new ArrayList<>();

    public void insertOne(Vehicle vehicle) {
        vehicleList.add(vehicle);
    }

    public Optional<Vehicle> findOne(Predicate<Vehicle> filter) {
        return vehicleList
            .stream()
            .filter(filter)
            .findFirst();
    }

    public ArrayList<Vehicle> listVehicles() {
        return vehicleList;
    }

    public ArrayList<Vehicle> listVehicles(Predicate<Vehicle> filter) {
        return vehicleList
            .stream()
            .filter(filter)
            .collect(Collectors.toCollection(ArrayList::new));
    }
}
