package io.core.app.repositories;


import io.core.app.models.dto.ProductDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDtoStagingRepository extends CrudRepository<ProductDto, Long> {



}
