package com.innogent.abhi.dto;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

public class EmployeeDTOSerializer extends JsonSerializer<EmployeeDTO> {
	
    @Override
    public void serialize(EmployeeDTO employeeDTO, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", employeeDTO.getId());
        jsonGenerator.writeStringField("name", employeeDTO.getName());
        jsonGenerator.writeObjectField("doj", employeeDTO.getDoj());
        jsonGenerator.writeNumberField("company", employeeDTO.getCompanyId());
        jsonGenerator.writeStringField("companyName", employeeDTO.getCompanyName());
        jsonGenerator.writeEndObject();
    }
}

