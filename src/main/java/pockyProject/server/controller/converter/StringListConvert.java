package pockyProject.server.controller.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Converter;

import java.util.List;

@Converter
public class StringListConvert implements AttributeConverter<List<String>, String> {



        private final ObjectMapper objectMapper = new ObjectMapper();

        @Override
        public String convertToDatabaseColumn(List<String> dataInfo) {
            try {
                return objectMapper.writeValueAsString(dataInfo);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e.getMessage());
            }
        }

        @Override
        public List<String> convertToEntityAttribute(String data) {
            try {
                return objectMapper.readValue(data, new TypeReference<>() {});
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }
