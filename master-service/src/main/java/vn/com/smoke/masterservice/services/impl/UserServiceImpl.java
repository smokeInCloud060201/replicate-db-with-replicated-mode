package vn.com.smoke.masterservice.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.com.smoke.masterservice.dtos.UserDTO;
import vn.com.smoke.masterservice.entities.User;
import vn.com.smoke.masterservice.repository.UserRepository;
import vn.com.smoke.masterservice.services.UserService;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDTO createNewUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setAddress(userDTO.getAddress());
        user = userRepository.save(user);
        return buildDTO(user);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setAddress(userDTO.getAddress());
        user = userRepository.save(user);
        return buildDTO(user);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public Page<User> getAllUsers() {
        PageRequest pageRequest = PageRequest.of(0, Integer.MAX_VALUE);
        return userRepository.findAll(pageRequest);
    }

    UserDTO buildDTO(User user) {
        return UserDTO.builder()
               .id(String.valueOf(user.getId()))
               .name(user.getName())
               .email(user.getEmail())
               .address(user.getAddress())
               .build();
    }
}
