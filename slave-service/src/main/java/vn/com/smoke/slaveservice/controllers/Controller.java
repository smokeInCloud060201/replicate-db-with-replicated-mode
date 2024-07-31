package vn.com.smoke.slaveservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.com.smoke.slaveservice.entities.User;
import vn.com.smoke.slaveservice.services.UserService;

@RestController
@RequiredArgsConstructor
public class Controller {

    private final UserService userService;


    @GetMapping
    public ResponseEntity<Page<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

}
