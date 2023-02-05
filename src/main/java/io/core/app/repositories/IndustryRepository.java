
package io.core.app.repositories;


import io.core.app.models.Industry;
import io.core.app.models.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

@Repository
@RestResource(path = "industries", rel = "industries")
public interface IndustryRepository extends PagingAndSortingRepository<Industry, String> {
}
