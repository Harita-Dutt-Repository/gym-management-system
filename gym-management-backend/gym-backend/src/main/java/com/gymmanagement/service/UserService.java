package com.gymmanagement.service;

import com.gymmanagement.model.User;
import com.gymmanagement.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> validateLogin(String username, String password) {
        return userRepository.findByUsername(username)
                .filter(u -> u.getPassword().equals(password));
    }
	public User saveUser(User user) {
    return userRepository.save(user);
	}

    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }
}
