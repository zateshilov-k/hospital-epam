package model;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.ResourceBundle;

public enum PrescriptionType {
    PROCEDURE {
        private static final String KEY = "procedure";
        @Override
        public String toString() {
            return ResourceBundle.getBundle(bundleName, locale).getString(KEY);
        }
    },
    DRUG {
        private static final String KEY = "drug";
        @Override
        public String toString() {
            return ResourceBundle.getBundle(bundleName, locale).getString(KEY);
        }
    },
    OPERATION {
        private static final String KEY = "operation";
        @Override
        public String toString() {
            return ResourceBundle.getBundle(bundleName, locale).getString(KEY);
        }
    };
    public static Locale locale;
    String bundleName = "internationalization.resource";
    public static class PrescriptionTypeAdapter implements JsonSerializer<Prescription> {
        public static Locale locale;
        String bundleName = "internationalization.resource";
        @Override
        public JsonElement serialize(Prescription prescription, Type type,
                                     JsonSerializationContext jsonSerializationContext) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.add("prescriptionId", new JsonPrimitive(prescription.getPrescriptionId()));
            jsonObject.add("description", new JsonPrimitive(prescription.getDescription()));
            jsonObject.add("patient", jsonSerializationContext.serialize(prescription.getPatient()));
            jsonObject.add("diagnosis", jsonSerializationContext.serialize(prescription.getDiagnosis()));
            ResourceBundle resourceBundle = ResourceBundle.getBundle(bundleName,locale);
            if(prescription.isDone()){
                jsonObject.add("done", new JsonPrimitive(resourceBundle.getString("prescriptionIsDone")));
            } else {
                jsonObject.add("done", new JsonPrimitive(resourceBundle.getString("prescriptionIsNotDone")));
            }
            //jsonObject.add("time", new JsonPrimitive(prescription.getTime().toString()));
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter
                    .ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(locale);
            jsonObject.add("time", new JsonPrimitive(dateTimeFormatter.format(prescription.getTime())));

            jsonObject.add("type", new JsonPrimitive(prescription.getType().toString()));
            return jsonObject;
        }
    }
}

