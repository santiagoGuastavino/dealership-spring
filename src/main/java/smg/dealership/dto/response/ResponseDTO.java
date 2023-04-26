package smg.dealership.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ResponseDTO<T> {
    private int statusCode;
    private HttpStatus message;
    private T payload;
    private List<String> errors;
}
