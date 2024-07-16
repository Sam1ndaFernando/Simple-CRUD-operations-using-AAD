package dto;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class StudentDto implements Serializable {
    private String id;
    private String name;
    private String email;
    private String city;
    private String level;

}
