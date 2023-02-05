
package io.core.app.repositories;


import io.core.app.models.Industry;
import io.core.app.models.Sector;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

@Repository
@RestResource(path = "sectors", rel = "sectors")
public interface SectorRepository extends PagingAndSortingRepository<Sector, String> {
}
