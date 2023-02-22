package fr.sncf.comere.common.rest;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ErrorResponse {
    
    private final String message;

    private final String type;
}
