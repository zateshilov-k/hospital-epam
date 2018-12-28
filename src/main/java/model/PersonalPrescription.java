package model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonalPrescription {

    private long personalId;
    private long prescriptionId;

}