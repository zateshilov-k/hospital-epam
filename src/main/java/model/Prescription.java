package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prescription {

    private long prescriptionId;
    private String description;
    private Patient patient;
    private Diagnosis diagnosis;
    private boolean isDone;
    private LocalDateTime time;
    private PrescriptionType type;

}