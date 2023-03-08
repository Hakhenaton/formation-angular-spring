package fr.sncf.comere.users.models;

import java.util.Arrays;


public enum UserRole {
    USER("user"),
    MODERATOR("moderator"),
    ADMINISTRATOR("administrator");

    private UserRole(String stringValue){
        this.stringValue = stringValue;
    }

    private static final long serialVersionUID = 0L;

    private final String stringValue;

    public String serialize() {
        return this.stringValue;
    }

    public static UserRole deserialize(String repr){
        return Arrays.stream(UserRole.values())
            .filter(role -> role.stringValue.equals(repr))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException(String.format("invalid value \"%s\"", repr)));            
    }
}
