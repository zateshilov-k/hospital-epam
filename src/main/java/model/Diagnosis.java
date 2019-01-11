package model;

import com.google.gson.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Diagnosis {

    private long diagnosisId;
    private String description;
    private Personal personal;
    private Patient patient;
    private boolean isOpened;
    private LocalDateTime time;



}