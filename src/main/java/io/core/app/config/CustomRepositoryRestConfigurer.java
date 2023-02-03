package io.core.app.config;

import io.core.app.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.ExposureConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import javax.persistence.EntityManager;

@Configuration
public class CustomRepositoryRestConfigurer implements RepositoryRestConfigurer {

    @Autowired
    private EntityManager entityManager;

    @Override
    public void configureRepositoryRestConfiguration(
            RepositoryRestConfiguration config, CorsRegistry cors)
    {

        ExposureConfiguration configCustom = config.getExposureConfiguration();
        configCustom.forDomainType(Product.class).withItemExposure((metadata, httpMethods) -> httpMethods.disable(HttpMethod.PATCH));

        config.exposeIdsFor(Product.class);

        /*Class[] classes = entityManager.getMetamodel()
                .getEntities().stream().map(currentClass-> currentClass.getClass()).toArray(Class[]::new);
        config.exposeIdsFor(classes);
        *
         */

    }
}
