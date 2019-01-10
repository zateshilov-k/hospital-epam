package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient {

    private long patientId;
    private String firstName;
    private String lastName;
    private boolean isDischarged;
    private boolean isDeleted;

}