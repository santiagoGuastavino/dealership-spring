package smg.dealership.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import smg.dealership.entity.TechnicalService;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VehicleDetailsDTO {
    private String licensePlate;
    private String brand;
    private String model;
    private int manufacturingYear;
    private int numberOfKilometers;
    private String doors;
    private String price;
    private List<TechnicalService> services;
    private String countOfOwners;
}
