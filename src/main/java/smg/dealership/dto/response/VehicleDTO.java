package smg.dealership.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class VehicleDTO {
    private String id;
    private String brand;
    private String model;
    private int manufacturingYear;
    private String price;
}
