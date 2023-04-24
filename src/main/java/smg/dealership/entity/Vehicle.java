package smg.dealership.entity;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Vehicle {
    private String id;
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
