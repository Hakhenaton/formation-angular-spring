package fr.sncf.comere.common.repository;

import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.dao.EmptyResultDataAccessException;

import lombok.experimental.UtilityClass;

@UtilityClass
public class RepositoryHelpers {
    
    public static <T> Optional<T> queryAsOptional(Supplier<T> resultSupplier){

        try {
            final var value = resultSupplier.get();
            return Optional.of(value);
        } catch (EmptyResultDataAccessException ex){
            return Optional.empty();
        }
    }
}
