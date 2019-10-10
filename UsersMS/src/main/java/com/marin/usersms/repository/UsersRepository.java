package com.marin.usersms.repository;

import com.marin.usersms.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<UserEntity, Long> {

    UserEntity findByEmail(String email);

}
