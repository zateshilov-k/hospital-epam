package model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class Diagnosis {

    private long diagnosisId;
    private String description;
    private Personal personalId;
    private Patient patientId;
    private boolean isHealthy;

}