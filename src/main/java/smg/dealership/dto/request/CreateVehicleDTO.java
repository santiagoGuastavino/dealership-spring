package smg.dealership.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import smg.dealership.entity.TechnicalService;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateVehicleDTO {
    private String licensePlate;
    private String brand;
    private String model;
    private int manufacturingYear;
    private int numberOfKilometers;
    private String doors;
    private String price;
    private String currency;
    private List<TechnicalService> services;
    private String countOfOwners;
}
