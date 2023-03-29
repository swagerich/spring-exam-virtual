package com.erich.exam.services.impl;

import com.erich.exam.entity.Role;
import com.erich.exam.entity.User;
import com.erich.exam.dto.UserDto;
import com.erich.exam.exception.NotFoundException;
import com.erich.exam.exception.ResourceException;
import com.erich.exam.repository.RoleRepository;
import com.erich.exam.repository.UserRepository;
import com.erich.exam.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;

    private final RoleRepository roleRepo;

    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User create(UserDto userDto) {
        if (userRepo.existsByUserName(userDto.getUserName())) {
            throw new ResourceException("Username ya existe!!");
        }
        User user = User.builder()
                .userName(userDto.getUserName())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .phone(userDto.getPhone())
                .enabled(true)
                .build();
        Set<Role> roles = new HashSet<>();
        Optional<Role> userRole = roleRepo.findByRoleName("ROLE_USER");
        if (userRole.isPresent()) {
            roles.add(userRole.get());
            user.setRoles(roles);
        }
        return userRepo.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        return userRepo.findByUserNameContaining(username).orElseThrow(() -> new NotFoundException("Username no encontrado"));
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        if (id == null) {
            return;
        }
        userRepo.deleteById(id);
    }

    @Override
    public User update(User user, Long id) {
        return null;
    }
}
