package vn.com.smoke.masterservice.services;

import org.springframework.data.domain.Page;
import vn.com.smoke.masterservice.dtos.UserDTO;
import vn.com.smoke.masterservice.entities.User;

public interface UserService {

    UserDTO createNewUser(UserDTO userDTO);
    UserDTO updateUser(UserDTO userDTO, Long userId);
    void deleteUser(Long userId);
    Page<User> getAllUsers();
}
