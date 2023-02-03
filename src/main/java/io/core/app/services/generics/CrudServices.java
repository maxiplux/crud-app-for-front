package io.core.app.services.generics;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CrudServices<T> {
    Page<T> findAll(Pageable pageable);

    T create(T elememnt);


    Optional<T> UpdateById(long id, T element);

    Boolean deleteById(long id);

    Optional<T> findById(long id);
}
