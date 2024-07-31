package vn.com.smoke.slaveservice.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.com.smoke.slaveservice.entities.User;
import vn.com.smoke.slaveservice.repository.UserRepository;
import vn.com.smoke.slaveservice.services.UserService;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Page<User> getAllUsers() {
        PageRequest pageRequest = PageRequest.of(0, Integer.MAX_VALUE);
        return userRepository.findAll(pageRequest);
    }
}
