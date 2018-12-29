package model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Diagnosis {

    private long diagnosisId;
    private String description;
    private Personal personalId;
    private Patient patientId;
    private Date time;
    private boolean isHealthy;

}