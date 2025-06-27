package com.company.ecommerce.repository;

import com.company.ecommerce.domain.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
    Optional<Category> findByIdAndDeletedAtIsNull(long id);

    Optional<List<Category>> findAllByDeletedAtIsNull();
}
