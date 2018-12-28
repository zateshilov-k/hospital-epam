package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Diagnosis {

    private long diagnosisId;
    private String description;
    private Personal personal;
    private Patient patient;
    private boolean isHealthy;
    private LocalDateTime time;

}