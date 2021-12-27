package parenkov.models.lombok;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private String name;
    private String job;
    private Integer id;
    private String token;
    private String error;
    private String url;
    private String text;
}
