package model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Prescription {

    private long prescriptionId;
    private String description;
    private long personalId;
    private long patientId;
    private long diagnosisId;
    private boolean isDone;

}