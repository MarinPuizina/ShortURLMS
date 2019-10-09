package com.marin.usersms.ui.repository;

import com.marin.usersms.ui.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<UserEntity, Long> {
}
