package com.erich.exam.services;

import com.erich.exam.entity.User;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    User create(User registerDto);

    User findByUsername(String username);

    void deleteUser(Long id);

    User uploadImg(User user, Long id, MultipartFile file);

    Resource viewPhoto(Long id);
}
