package tn.esprit.biat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.biat.Entity.User;
import tn.esprit.biat.repository.UserRepository;
@Service
public class userService {
    // Inject the UserRepository
    @Autowired
    private UserRepository userRepository;

    public User getUserByUsername(String username) {
        // Find the user by username in the database
        User user = userRepository.findByUsername(username)
                .orElse(null);

        // Return the found user
        return user;
    }
}
