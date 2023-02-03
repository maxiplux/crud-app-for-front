package io.core.app.repositories;


import io.core.app.models.users.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

    boolean existsByEmail(String email);

    Boolean existsByUsername(String username);

    //@Query("select u from USER u where u.username=?1")
    //User findByUsername2(String username);

}
