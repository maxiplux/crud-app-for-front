package io.core.app.services.impl;



import io.core.app.models.users.Role;
import io.core.app.repositories.RoleRepository;
import io.core.app.services.RoleServices;
import io.core.app.services.generics.CrudServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
public class RoleServicesImpl extends CrudServicesImpl<Role> implements RoleServices<Role> {

    @Autowired
    private RoleRepository roleRepository;


    @PostConstruct
    public void posContructor() {
        this.setRepository(roleRepository);
    }


    public Optional<Role> UpdateById(long id, Role element) {
        Optional<Role> optionalCurrentCompany = this.repository.findById(id);
        if (optionalCurrentCompany.isPresent()) {
            Role currentProduct = optionalCurrentCompany.get();

            if (element.getName() != null) {
                currentProduct.setName(element.getName());
            }


            return Optional.of((Role) this.repository.save(currentProduct));
        }
        return Optional.empty();

    }


}
