package com.example.healthcaremanagement.service.UserImpl;

import com.example.healthcaremanagement.entity.User;
import com.example.healthcaremanagement.entity.Usertype;
import com.example.healthcaremanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public User createUser(User user) {
        Optional<User> userFromDb = userRepository.findByEmail(user.getEmail());
        if (userFromDb.isEmpty()) {
            String password = user.getPassword();
            String encodedPassword = passwordEncoder.encode(password);
            user.setPassword(encodedPassword);
            user.setType(Usertype.USER);
        }
        return user;
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

}
