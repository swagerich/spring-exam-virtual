package com.erich.exam.controllers;

import static com.erich.exam.util.Path.PATH;

import com.erich.exam.entity.User;
import com.erich.exam.services.impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping(PATH + "user")
@CrossOrigin("*")
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> crear(@Valid @RequestBody User userDto) {
        return new ResponseEntity<>(userService.create(userDto), HttpStatus.CREATED);
    }

    @GetMapping("{username}")
    public ResponseEntity<?> getUserName(@PathVariable String username) {
        return new ResponseEntity<>(userService.findByUsername(username), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/profile/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updatePhoto(User user, @PathVariable Long id, @RequestPart("file") MultipartFile file) {
        return new ResponseEntity<>(userService.uploadImg(user, id, file), HttpStatus.CREATED);

    }

    @GetMapping("/view/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> viewProfile(@PathVariable Long id) {
        Resource resource = userService.viewPhoto(id);
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(resource);

    }
}
