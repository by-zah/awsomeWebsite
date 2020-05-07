package ua.khnu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.khnu.entity.User;
import ua.khnu.exception.LoginException;
import ua.khnu.exception.ValidationException;
import ua.khnu.reposetory.UserRepository;
import ua.khnu.util.MD5Hash;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    private final UserRepository repository;

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
        return getFirstOptionalFromList(repository.query(GET_USER_BY_ID, id));
    }

    private static final String GET_USER_BY_EMAIL = "SELECT * FROM users WHERE email=?";

    public Optional<User> getUserByEmail(String email) {
        return getFirstOptionalFromList(repository.query(GET_USER_BY_EMAIL, email));
    }

    private Optional<User> getFirstOptionalFromList(List<User> users) {
        if (users.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(users.get(0));
    }

    public boolean isEmailContainsInRepo(String email) {
        Optional<User> optional = getUserByEmail(email);
        return optional.isPresent();
    }

    public User getValidUserByEmailAndPassword(String email, String psw) {
        Optional<User> optional = getUserByEmail(email);
        if (optional.isEmpty()) {
            throw new LoginException("This email dosen't exists");
        }
        if (!optional.map(User::getPassword).get().equals(psw)) {
            throw new LoginException("Password not matches");
        }
        return optional.get();
    }

    public boolean valid(String psw2, User user) throws ValidationException {
        String regex = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";
        if (!(psw2.equals(user.getPassword()) && psw2.length() > 0)) {
            throw new ValidationException("The password you have entered is not valid!" +
                    " Password must contain at least 1 lowercase " +
                    "1 uppercase alphabetical character, 1 numeric character and have length more than 6 characters"
            );
        }
        if (!user.getEmail().matches(regex)) {
            throw new ValidationException("Email not valid");
        }
        if (!(user.getNumber().length() > 7)) {
            throw new ValidationException("Check you phone number");
        }
        if (isEmailContainsInRepo(user.getEmail())) {
            throw new ValidationException("This email already exists");
        }
        return true;
    }
}
