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

    Optional<User> findByIdAndDeletedFalse(String id);

    Optional<User> findByPhoneNumberAndDeletedFalse(String phoneNumber);

    @Modifying
    @Query("""
            update users set deleted = true where id = ?1
            """)
    void deleteByIdMy(String id);

    boolean existsByUsernameAndDeletedFalse(String username);
    boolean existsByEmailAndDeletedFalse(String email);

    boolean existsByIdAndDeletedFalse(String id);

}
