package com.erich.exam.services;

import com.erich.exam.entity.User;
import com.erich.exam.dto.UserDto;

public interface UserService {

    User create(UserDto registerDto);

    User findByUsername(String username);

    void deleteUser(Long id);

    User update(User user,Long id);
}
