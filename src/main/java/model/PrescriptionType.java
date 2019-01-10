package model;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.Locale;
import java.util.ResourceBundle;

public enum PrescriptionType {
    PROCEDURE {
        private static final String KEY = "procedure";
        @Override
        public String toString() {
            return ResourceBundle.getBundle(bundleName,locale).getString(KEY);
        }
    },
    DRUG {
        private static final String KEY = "drug";
        @Override
        public String toString() {
            return ResourceBundle.getBundle(bundleName,locale).getString(KEY);
        }
    },
    OPERATION {
        private static final String KEY = "operation";
        @Override
        public String toString() {
            return ResourceBundle.getBundle(bundleName,locale).getString(KEY);
        }
    };

    public static Locale locale;
    String bundleName = "internationalization.resource";

    public static class PrescriptionTypeAdapter implements JsonSerializer<Prescription> {
        @Override
        public JsonElement serialize(Prescription prescription, Type type,
                                     JsonSerializationContext jsonSerializationContext) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.add("prescriptionId",new JsonPrimitive(prescription.getPrescriptionId()));
            jsonObject.add("description",new JsonPrimitive(prescription.getDescription()));
            jsonObject.add("patient",jsonSerializationContext.serialize(prescription.getPatient()));
            jsonObject.add("diagnosis",jsonSerializationContext.serialize(prescription.getDiagnosis()));
            jsonObject.add("done",new JsonPrimitive(prescription.isDone()));
            jsonObject.add("time",new JsonPrimitive(prescription.getTime().toString()));
            jsonObject.add("type", new JsonPrimitive(prescription.getType().toString()));
            return jsonObject;
        }
    }
}

