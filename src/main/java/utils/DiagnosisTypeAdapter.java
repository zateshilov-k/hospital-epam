package utils;

import com.google.gson.*;
import model.Diagnosis;

import java.lang.reflect.Type;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.ResourceBundle;

public class DiagnosisTypeAdapter implements JsonSerializer<Diagnosis> {
    public static Locale locale;
    private String bundleName = "internationalization.resource";
    @Override
    public JsonElement serialize(Diagnosis diagnosis, Type type, JsonSerializationContext jsonSerializationContext) {
        Locale.setDefault(Locale.forLanguageTag("en"));
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("diagnosisId",new JsonPrimitive(diagnosis.getDiagnosisId()));
        jsonObject.add("description",new JsonPrimitive(diagnosis.getDescription()));
        jsonObject.add("personal",jsonSerializationContext.serialize(diagnosis.getPersonal()));
        jsonObject.add("patient",jsonSerializationContext.serialize(diagnosis.getPatient()));
        jsonObject.add("patient",jsonSerializationContext.serialize(diagnosis.getPatient()));
        ResourceBundle resourceBundle = ResourceBundle.getBundle(bundleName,locale);
        if (diagnosis.isOpened()) {
            jsonObject.add("isOpened",new JsonPrimitive(resourceBundle.getString("diagnosisIsOpened")));
        } else {
            jsonObject.add("isOpened",new JsonPrimitive(resourceBundle.getString("diagnosisIsNotOpened")));
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter
                .ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(locale);
        jsonObject.add("time", new JsonPrimitive(dateTimeFormatter.format(diagnosis.getTime())));
        return jsonObject;
    }
}