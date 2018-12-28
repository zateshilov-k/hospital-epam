package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonalPrescription {

    private long personalPrescriptionId;
    private PersonalPrescriptionType type;
    private Personal personal;
    private Prescription prescription;

}