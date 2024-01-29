package com.oasis.taskmanagementapp.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.oasis.taskmanagementapp.entities.enums.Status;

import java.io.IOException;

public class StatusSerializer extends JsonSerializer<Status> {


    @Override
    public void serialize(Status status, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        String serializedValue = status.name().replaceAll("_", "");
        jsonGenerator.writeString(serializedValue);

//        String serializedValue = status.name();
//
//        // Check for specific cases and adjust as needed
//        if ("INPROGRESS".equals(serializedValue)) {
//            serializedValue = "IN_PROGRESS";
//        }
//        // Add more cases if needed
//
//        // Serialize TodoStatus enum to a string representation suitable for the frontend
//        jsonGenerator.writeString(serializedValue);

    }
}
