package com.talkon.talkon.repositories.user;

import com.talkon.talkon.entities.user.User;
import com.talkon.talkon.repositories.base.AbstractRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends AbstractRepository<User, String> {

    @Query(value = "select u.* from users u where u.delete = 0 and u.id = ?1", nativeQuery = true)
    Optional<User> findById(String id);

    @Query(value = "select u.* from users u where u.is_deleted = 0 and u.username = ?1", nativeQuery = true)
    Optional<User> findByUsername(String username);


}
