package ua.khnu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.khnu.entity.User;
import ua.khnu.reposetory.UserRepository;
import ua.khnu.util.MD5Hash;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    private UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    /**
     * @param user User object that should be added to DB
     * @return User object with generated id field
     */
    public User createNewUser(User user) {
        user.setPassword(MD5Hash.getHash(user.getPassword()));
        return repository.add(user);
    }

    public boolean deleteUserById(int id) {
        return repository.delete(id);
    }

    public boolean updateUser(User user) {
        return repository.update(user);
    }

    private static final String GET_USER_BY_ID = "SELECT * FROM users WHERE id=?";

    public Optional<User> getUserById(int id) {
        List<User> users = repository.query(GET_USER_BY_ID, id);
        if (users.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(users.get(0));
    }
}
