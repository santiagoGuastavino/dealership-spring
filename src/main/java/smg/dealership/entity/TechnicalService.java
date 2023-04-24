package smg.dealership.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TechnicalService {
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate date;
    private String kilometers;
    private String description;
}
