package com.talkon.talkon.repositories.user.user;

import com.talkon.talkon.entities.user.Account;
import com.talkon.talkon.repositories.base.AbstractRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AccountRepository extends AbstractRepository<Account,String> {
    @Query(nativeQuery = true,value = "select account.talk_coins from users.account\n" +
            "where account.user_id = :id")
    Integer getCoinsByUserId(String id);

    Optional<Account> findByUserIdAndDeletedFalse(String userId);
}
