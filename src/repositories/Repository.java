package repositories;

import java.util.List;
import java.util.Optional;

// Generic repository interface — T is the entity type, ID is the key type
// Using generics means we only define these operations once
public interface Repository<T, ID> {

    // Create or update an entity
    void save(T entity);

    // Find by ID — returns Optional to avoid null checks
    Optional<T> findById(ID id);

    // Return all entities
    List<T> findAll();

    // Delete by ID — ignored if ID does not exist
    void delete(ID id);
}