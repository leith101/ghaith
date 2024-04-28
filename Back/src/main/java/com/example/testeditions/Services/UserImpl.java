package com.example.testeditions.Services;

import com.example.testeditions.DTO.LoginDTO;
import com.example.testeditions.DTO.UserDTO;
import com.example.testeditions.Entites.Preferences;
import com.example.testeditions.Entites.User;
import com.example.testeditions.Exception.UsernameAlreadyUsedException;
import com.example.testeditions.Payload.Response.LoginMesage;
import com.example.testeditions.Repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Inject PasswordEncoder



    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public User save(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setNom(userDTO.getNom());
        user.setEmail(userDTO.getEmail());
        // Encode the plain-text password using BCryptPasswordEncoder
        user.setPassword(this.passwordEncoder.encode(userDTO.getPassword()));
        user.setTelephone(userDTO.getTelephone());
        user.setRole(userDTO.getRole());
        user.setRating(user.getRating());

        return userRepository.save(user);
    }

    @Override
    public User update(Long userId, User userDTO) {
        // Retrieve the existing user from the database
        User existingUser = userRepository.findById(userId).orElse(null);

            existingUser.setNom(userDTO.getNom());
            existingUser.setEmail(userDTO.getEmail());
        existingUser.setTelephone(userDTO.getTelephone());
        existingUser.setRole(userDTO.getRole());
            // Save the updated user to the database
            return userRepository.save(existingUser);


    }

    @Override
    public UserDTO loginUser(LoginDTO loginDTO) {
        String msg = "";
        User user1 = userRepository.findByEmail(loginDTO.getEmail());
        if (user1 != null) {

            if (user1.isBanned()) {
                // Return null or an appropriate response to indicate that the user is banned
                return null;
            }
            String password = loginDTO.getPassword();
            String encodedPassword = user1.getPassword();
            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);

            if (isPwdRight) {
                Optional<User> user = userRepository.findOneByEmailAndPassword(loginDTO.getEmail(), encodedPassword);
                if (user.isPresent()) {
                    // Create and return UserDetailsDTO with user information
                    UserDTO userDetailsDTO = new UserDTO();
                    userDetailsDTO.setId(user.get().getId());
                    userDetailsDTO.setNom(user.get().getNom());
                    userDetailsDTO.setEmail(user.get().getEmail());
                    userDetailsDTO.setTelephone(user.get().getTelephone());
                    userDetailsDTO.setRating(user.get().getRating());
                    userDetailsDTO.setRole(user.get().getRole());

                    // Add any other fields you want to include

                    return userDetailsDTO;

                }
            }
        }
        return null;
    }
    @Override
    public User addPreferencesToUser(Long userId, List<String> selectedTypes) {
        // Fetch the user by ID
        User user = userRepository.findById(userId).orElse(null);

        if (user != null) {
            // Create preferences and associate them with the user
            List<Preferences> preferencesList = new ArrayList<>();
            for (String selectedType : selectedTypes) {
                Preferences preferences = new Preferences();
                preferences.setSelectedTypes(Collections.singletonList(selectedType));
                preferences.setUser(user);
                preferencesList.add(preferences);
            }

            // Set the preferences for the user and save
            user.setPreferences(preferencesList);
            userRepository.save(user);
        }
            return user;

    }



    @Override
    public User disconnect(User user) {
        if (user == null) {
            return null;
        }

        User dbUser = userRepository.findByNom(user.getNom());
        if (dbUser == null) {
            return user;
        }

        dbUser.setConnected(false);
        return userRepository.save(dbUser);
    }

    @Override
    public void banUserByEmail(String email) {

        User user = userRepository.findByEmail(email);
        if (user != null) {
            user.setBanned(true);
            userRepository.save(user);
        }

    }

    @Override
    public long countBannedUsers() {
        return userRepository.countByBannedTrue();    }





    @Override
    public String generateVerificationCode() {
        // Generate a random 6-digit verification code
        Random random = new Random();
        int code = 1000 + random.nextInt(9000);
        return String.valueOf(code);
    }

}



