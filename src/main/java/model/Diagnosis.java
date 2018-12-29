package model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Diagnosis {

    private long diagnosisId;
    private String description;


    private Personal personal;
    private Patient patient;
    private Date time;
    private boolean isHealthy;

}