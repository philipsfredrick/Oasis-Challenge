package com.oasis.taskmanagementapp.utils;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.oasis.taskmanagementapp.entities.enums.Status;

import java.io.IOException;

public class StatusDeserializer extends JsonDeserializer<Status> {

    @Override
    public Status deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        String statusString = jsonParser.getText();
        if ("INPROGRESS".equalsIgnoreCase(statusString)) {
            return Status.IN_PROGRESS;
        }
        return Status.valueOf(statusString);
    }
}

