package vn.com.smoke.slaveservice.services;

import org.springframework.data.domain.Page;
import vn.com.smoke.slaveservice.entities.User;

public interface UserService {
    Page<User> getAllUsers();
}
