package io.core.app.services.generics;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

@Data
public abstract class CrudServicesImpl<T> implements CrudServices<T> {


    protected PagingAndSortingRepository repository;


    public CrudServicesImpl(PagingAndSortingRepository repository) {
        this.repository = repository;
    }

    public CrudServicesImpl() {

    }


    @Override
    public Page<T> findAll(Pageable pageable) {
        return this.repository.findAll(pageable);
    }

    @Override
    public T create(T elememnt) {
        return (T) this.repository.save(elememnt);
    }


    @Override
    public abstract Optional<T> UpdateById(long id, T element);


    @Override
    public Boolean deleteById(long id) {
        this.repository.deleteById(id);
        return this.repository.findById(id).isPresent();
    }

    @Override
    public Optional<T> findById(long id) {
        return this.repository.findById(id);
    }
}
