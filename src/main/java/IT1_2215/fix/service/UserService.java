package IT1_2215.fix.service;

import IT1_2215.fix.entity.User;
import IT1_2215.fix.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // Регистрация пользователя с ролью
    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Шифруем пароль
        user.setRole(user.getRole().toUpperCase()); // Убедимся, что роль в верхнем регистре
        return userRepository.save(user);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    public void changeUserRole(Long userId, String newRole) {
        userRepository.findById(userId).ifPresent(user -> {
            user.setRole(newRole.toUpperCase()); // ADMIN, USER, DEV
            userRepository.save(user);
        });
    }

}
