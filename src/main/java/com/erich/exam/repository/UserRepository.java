package com.erich.exam.repository;

import com.erich.exam.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUserNameContaining(String name);

    Boolean existsByUserName(String username);

}