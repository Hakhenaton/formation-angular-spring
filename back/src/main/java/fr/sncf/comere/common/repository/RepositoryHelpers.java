package fr.sncf.comere.common.repository;

import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.dao.EmptyResultDataAccessException;

import lombok.experimental.UtilityClass;

@UtilityClass
public class RepositoryHelpers {
    
    /**
     * Permet de faire une requête JDBC qui potentiellement
     * lève une exception de type {@link EmptyResultDataAccessException}
     * pour ensuite encapsuler le résultat un objet {@link Optional}.
     * @param <T> Le type de la valeur de retour de la requête.
     * @param resultSupplier Une fonction permettant d'exécuter la requête et qui renvoie la valeur
     * @return Si une exception de type {@link EmptyResultDataAccessException} est levé lors de la requête, on renvoie un
     * {@link Optional} vide, sinon un {@link Optional} contenant la valeur de retour
     * de la requête.
     */
    public static <T> Optional<T> queryAsOptional(Supplier<T> resultSupplier){

        try {
            final var value = resultSupplier.get();
            return Optional.of(value);
        } catch (EmptyResultDataAccessException ex){
            return Optional.empty();
        }
    }
}
