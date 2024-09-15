package com.emazon.microservicio_transaccion.domain.validation;

import com.emazon.microservicio_transaccion.domain.util.DomainConstants;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ValidationFailureMessage {
    public String parseFailureMessage(String errorMessage) {
        try {
            int jsonStartIndex = errorMessage.indexOf(DomainConstants.START_MESSAGE_SYMBOLS);

            if (jsonStartIndex == -1) {
                return errorMessage.substring(0, Math.min(errorMessage.length(), 255));
            }

            String jsonPart = errorMessage.substring(jsonStartIndex);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonPart);

            JsonNode messageNode = rootNode.path(0).path(DomainConstants.PATH_MESSAGE);
            JsonNode statusNode = rootNode.path(0).path(DomainConstants.PATH_STATUS);

            if (messageNode.isMissingNode() || statusNode.isMissingNode()) {
                return errorMessage.substring(0, Math.min(errorMessage.length(), 255));
            }

            String message = messageNode.asText();
            String status = statusNode.asText();

            return String.format("Error: %s - Status: %s", message, status);
        } catch (Exception ex) {
            return errorMessage.substring(0, Math.min(errorMessage.length(), 255));
        }
    }

}
