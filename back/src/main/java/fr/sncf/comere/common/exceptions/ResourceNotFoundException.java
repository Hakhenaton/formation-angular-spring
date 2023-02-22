package fr.sncf.comere.common.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(Class<?> clz){
        super(String.format("resource of type %s was not found", clz.getSimpleName()));
    }
}
