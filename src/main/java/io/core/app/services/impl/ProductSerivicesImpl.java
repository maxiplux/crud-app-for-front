package io.core.app.services.impl;

import io.core.app.exceptions.ResourceNotFoundException;
import io.core.app.models.Product;
import io.core.app.repositories.ProductRepository;
import io.core.app.services.ProductSerivices;
import io.core.app.services.generics.CrudServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
public class ProductSerivicesImpl  extends CrudServicesImpl<Product> implements ProductSerivices {
    @Autowired
    private ProductRepository productRepository;


    @PostConstruct
    public void postContructor() {
        this.repository = this.productRepository;
    }

    @Override
    public Optional<Product> UpdateById(long id, Product element) {
        var currentProduct = this.productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product not found with id: "+id));
        if (element.getName() != null) {
            currentProduct.setName(element.getName());
        }
        if (element.getRank() != null) {
            currentProduct.setRank(element.getRank());
        }
        if (element.getIndustry() != null) {
            currentProduct.setIndustry(element.getIndustry());
        }
        if (element.getSector() != null) {
            currentProduct.setSector(element.getSector());
        }

        if (element.getWebsite() != null) {
            currentProduct.setWebsite(element.getWebsite());
        }


        return Optional.of(this.productRepository.save(currentProduct));
    }

}
