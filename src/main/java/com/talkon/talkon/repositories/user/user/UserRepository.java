package com.talkon.talkon.repositories.user.user;

import com.talkon.talkon.entities.user.User;
import com.talkon.talkon.repositories.base.AbstractRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface UserRepository extends AbstractRepository<User, String> {

    @Query(value = "select u.* from users u where u.delete = 0 and u.id = ?1", nativeQuery = true)
    Optional<User> findById(String id);

    Optional<User> findByPhoneNumberAndDeletedFalse(String phoneNumber);

    @Modifying
    @Query("""
            update users set deleted = true where id = ?1
            """)
    void deleteByIdMy(String id);

}
