package model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Diagnosis {

    private long diagnosisId;
    private String description;
    private Personal personalId;
    private Patient patientId;
    private boolean isHealthy;

}