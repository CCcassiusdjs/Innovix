package com.innovix.repository;

import com.innovix.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Category entity.
 * <p>
 * This interface extends JpaRepository to provide CRUD operations for Category entities.
 * </p>
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    /**
     * Finds a category by its name.
     *
     * @param name The name of the category.
     * @return The category with the specified name.
     */
    Category findByName(String name);
}
