package com.erich.exam.services.impl;

import com.erich.exam.dto.EmailCustomDto;
import com.erich.exam.entity.Role;
import com.erich.exam.entity.User;
import com.erich.exam.exception.NotFoundException;
import com.erich.exam.exception.ResourceException;
import com.erich.exam.repository.RoleRepository;
import com.erich.exam.repository.UserRepository;
import com.erich.exam.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;

    private final RoleRepository roleRepo;

    private final PasswordEncoder passwordEncoder;

    private final EmailServiceImpl emailService;

    @Override
    @Transactional
    public User create(User userDto) {
        if (userRepo.existsByUserName(userDto.getUsername())) {
            throw new ResourceException("Username ya existe!!");
        }
        if(userRepo.existsByEmail(userDto.getEmail())){
            throw  new ResourceException("Email ya existe!!");
        }
        User user = User.builder()
                .userName(userDto.getUsername())
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
        EmailCustomDto emailCustomDto = EmailCustomDto.builder().toEmails(Collections.singletonList(userDto.getEmail())).build();
        emailService.sendEmailRegister(emailCustomDto,userDto.getUsername());
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
    @Transactional
    public User uploadImg(User user, Long id, MultipartFile file) {
        User u = userRepo.findById(id).orElseThrow(() -> new NotFoundException("Usuario con el id : '" + id + "' no encontrado!"));
        //u.setUserName(user.getUsername());
        u.setPhone(user.getPhone());
        if (file != null && !file.isEmpty()) {
            try {
                u.setProfile(file.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return userRepo.save(u);
    }

    @Override
    @Transactional(readOnly = true)
    public Resource viewPhoto(Long id) {
        User user = userRepo.findById(id).orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
        if(user.getProfileHashCode().equals(1)){
            throw new ResourceException("El usuario no contiene ninguna Foto !");
        }
        return new ByteArrayResource(user.getProfile());
    }
}
