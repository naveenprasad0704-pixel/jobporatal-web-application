package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project3.example.project3.model.User;
import project3.example.project3.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Register a new user
     */
    public User registerUser(User user) {
        // Here you could hash password before saving (optional)
        return userRepository.save(user);
    }

    /**
     * Authenticate a user
     */
    public boolean authenticate(String email, String password) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            return user.getPassword().equals(password); // For demo purposes; use hashing in production
        }
        return false;
    }

    /**
     * Find a user by email
     */
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}