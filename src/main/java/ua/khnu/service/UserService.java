package ua.khnu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.khnu.entity.User;
import ua.khnu.exception.LoginException;
import ua.khnu.exception.ValidationException;
import ua.khnu.reposetory.UserRepository;

import java.util.List;
import java.util.Optional;

import static ua.khnu.util.MD5Hash.getHash;

@Component
public class UserService {
    private static final String GET_USER_BY_ID = "SELECT * FROM users WHERE id=?";
    private static final String GET_USER_BY_EMAIL = "SELECT * FROM users WHERE email=?";
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
        user.setPassword(getHash(user.getPassword()));
        return repository.add(user);
    }

    public boolean deleteUserById(int id) {
        return repository.delete(id);
    }

    public boolean updateUser(User user) {
        return repository.update(user);
    }

    public Optional<User> getUserById(int id) {
        return getFirstOptionalFromList(repository.query(GET_USER_BY_ID, id));
    }

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
        User user = getUserByEmail(email).orElseThrow(() -> new LoginException("Повторите ввод, эмаил или пароль введены неверно"));
        if (!user.getPassword().equals(getHash(psw))) {
            throw new LoginException("Повторите ввод, эмаил или пароль введены неверно");
        }
        return user;
    }

    public boolean valid(String psw2, User user) throws ValidationException {
        String regex = "^([a-zA-Z0-9_\\-.]+)@([a-zA-Z0-9_\\-.]+)\\.([a-zA-Z]{2,5})$";
        if (!(psw2.equals(user.getPassword()) && psw2.length() > 0)) {
            throw new ValidationException(
                    "Пароль должен содержать как минимум 1 большую и 1 маленькую буквы" + System.lineSeparator() +
                            " 1 цифру" + System.lineSeparator() +
                            " и иметь длину больше 6 знаков");
        }
        if (!user.getEmail().matches(regex)) {
            throw new ValidationException("Емаил введен неверно");
        }
        if ((user.getNumber().length() <= 7)) {
            throw new ValidationException("Проверте телефонный номер");
        }
        if (isEmailContainsInRepo(user.getEmail())) {
            throw new ValidationException("Такой эмаил уже существует");
        }
        return true;
    }
}
