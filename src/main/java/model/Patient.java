package model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Patient {

    private long patientId;
    private String firstName;
    private String lastName;
    private boolean isDischarged;

}