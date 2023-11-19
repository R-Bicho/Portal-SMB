package com.portalSMB.portalSMB.controller.restApi.deserialize;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.portalSMB.portalSMB.model.entities.Coverage;
import com.portalSMB.portalSMB.model.entities.GenericFailure;
import com.portalSMB.portalSMB.model.entities.Voice;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;


public class AccountDeserialize extends JsonDeserializer<GenericFailure> {
    @Override
    public GenericFailure deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {

        ObjectCodec objectCodec = jsonParser.getCodec();
        JsonNode jsonNode = objectCodec.readTree(jsonParser);

        String originNumber = String.valueOf(jsonNode.get("originNumber"));
        String messageFailure = String.valueOf(jsonNode.get("messageFailure"));
        String roaming = String.valueOf(jsonNode.get("roaming"));
        String address = String.valueOf(jsonNode.get("address"));
        String chipTesting = String.valueOf(jsonNode.get("chipTesting"));
        String restarted = String.valueOf(jsonNode.get("restarted"));
        String failureHourTemporary = String.valueOf(jsonNode.get("failureHour"));
        String dateFailure = String.valueOf(jsonNode.get("dateFailure"));
        Date failureHour = Timestamp.valueOf(failureHourTemporary);

        if(jsonNode.get("typeFailure") != null)
        {
            String typeFailure = String.valueOf(jsonNode.get("typeFailure"));
            String destinationNumber = String.valueOf(jsonNode.get("messageFailure"));
            return new Voice(null, originNumber,
                    messageFailure,
                    roaming,
                    null,
                    typeFailure,
                    destinationNumber);

        }
        return new Coverage(null, originNumber,
                messageFailure,
                roaming,
                null,
                address,
                chipTesting,
                restarted,
                dateFailure,
                failureHour.toString());
    }
}
