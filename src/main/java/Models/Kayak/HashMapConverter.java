package Models.Kayak;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;

public class HashMapConverter implements AttributeConverter<Map<String, Object>, String> {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Map<String, Object> info) {

        String infoJson = null;
        try {
            infoJson = objectMapper.writeValueAsString(info);
        } catch (final JsonProcessingException e) {
            System.out.println("Zesrało się");
        }
        return infoJson;
    }

    @Override
    public Map<String, Object> convertToEntityAttribute(String infoJson) {
        Map<String, Object> info = null;
        try {
            info = objectMapper.readValue(infoJson, new TypeReference<HashMap<String, Object>>() {}); 
        } catch ( final IOException e) {
            System.out.println("Znoowu się zesrało");
        }

        return info;
    }
}
