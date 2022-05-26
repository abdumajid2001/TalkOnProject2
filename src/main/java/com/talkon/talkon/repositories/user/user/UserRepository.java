package com.talkon.talkon.repositories.user.user;

import com.talkon.talkon.entities.user.User;
import com.talkon.talkon.projections.UserContactStatusProjection;
import com.talkon.talkon.repositories.base.AbstractRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
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

    @Modifying
    @Query(nativeQuery = true, value = """
            update users.users set is_online=:status where username = :username
            """)
    void changeStatus(String username, boolean status);

    @Modifying
    @Query(nativeQuery = true, value = """
            update users.users set is_online=:status, last_seen = now() where username = :username
            """)
    void changeStatusAndLastSeen(String username, boolean status);




    @Query(value = """
            select (case when t.user_id = (select id from users.users where username = :username) then s.user_id
                        else t.user_id end ) as userId
            from conversation.chats
                     join users.mentors t on chats.mentor_id = t.id
                     join users.mentees s on chats.mentee_id = s.id
            """,
            nativeQuery = true)
    List<String> getAllContactId(String username);

    @Query(nativeQuery = true, value = "select id, is_online as \"isOnline\" from users.users where username = :username")
    UserContactStatusProjection getUserContactStatus(String username);

    boolean existsByUsernameAndDeletedFalse(String username);
    boolean existsByEmailAndDeletedFalse(String email);

    boolean existsByIdAndDeletedFalse(String id);

}
