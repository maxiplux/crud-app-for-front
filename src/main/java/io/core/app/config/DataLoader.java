package io.core.app.config;

import com.opencsv.bean.CsvToBeanBuilder;
import io.core.app.models.dto.CompanyDto;
import io.core.app.models.dto.ProductDto;
import io.core.app.models.users.Role;
import io.core.app.repositories.ProductDtoStagingRepository;
import io.core.app.repositories.RoleRepository;
import io.core.app.services.IUserService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
//import user
import io.core.app.models.users.User;

import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Configuration
@Slf4j
public class DataLoader implements ApplicationRunner {
    @Value("${dataset.sourcefile}")
    private String datasetFile;

    @Value("${app.data.loader.user.admin.password}")
    private String adminPassword;
    @Autowired
    //@Qualifier("CustomUserDetailsService")
    private IUserService userServices;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ProductDtoStagingRepository productDtoStagingRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
       try {
           if (this.userServices.count()==0)
           {
               this.createUser();
           }


       }    catch (Exception e) {
            log.error("Error while loading data", e.getMessage());
        }

        //loadCsvCompanies();
    }

    private void createUser() {
        Role role=this.roleRepository.save(Role.builder().name("ROLE_ADMIN").build());
        Role role2=this.roleRepository.save(Role.builder().name("ROLE_USER").build());
        List<Role> roles=List.of(role,role2);

        User user= User.builder().username("admin").enabled(true).password(this.passwordEncoder.encode(this.adminPassword)).roles(roles).build();
        this.userServices.save(user);
    }

    @SneakyThrows
    private void loadCsvCompanies()
    {

        ClassLoader classLoader = getClass().getClassLoader();
        String fileName = classLoader.getResource("Fortune1000.csv").getFile();
        log.info("Start Loading Companies In memory");
        long start = System.nanoTime();
        List<ProductDto> beans = new CsvToBeanBuilder(new FileReader(fileName))
                .withType(ProductDto.class)
                .withSkipLines(1)
                .build()
                .parse();
        long finish = System.nanoTime();
        long timeElapsed = finish - start;
        log.info("Time elapsed: {} ms Loading into Memory", timeElapsed / 1000000);

        start = System.nanoTime();
        List<CompanyDto> subset= new ArrayList<>();

        for (ProductDto companyDto:beans) {
            log.info("Current Cursor: {}",companyDto);
            this.productDtoStagingRepository.save(companyDto);

        }

        finish = System.nanoTime();
        timeElapsed = finish - start;
        log.info("Time elapsed: {} ms Printting from memory", timeElapsed / 1000000);

    }

}
