package model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class Patient {

    private long patientId;
    private String firstName;
    private String lastName;
    private boolean isDischarged;

}