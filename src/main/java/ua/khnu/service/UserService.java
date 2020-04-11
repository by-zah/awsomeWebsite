package ua.khnu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.khnu.entity.User;
import ua.khnu.reposetory.UserRepository;

@Component
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(int id){
        return userRepository.query("SELECT * FROM users where id="+id);
    }
}
