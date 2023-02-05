package io.core.app.controllers;

import io.core.app.controllers.generics.CrudController;
import io.core.app.models.Product;
import io.core.app.services.ProductSerivices;
import io.core.app.services.RoleServices;
import io.core.app.services.generics.CrudServices;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/products")
@Data
public class ProductController  extends CrudController<Product> {
    @Autowired
    private ProductSerivices productSerivices;

    @PostConstruct
    public void postContructor() {
        this.service = productSerivices;
    }

}
