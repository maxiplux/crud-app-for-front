package io.core.app.repositories;


import io.core.app.models.Product;
import io.core.app.models.dto.ProductDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

@Repository
@RestResource(path = "products", rel = "products")
public interface ProductRepository extends CrudRepository<Product, Long> {



}