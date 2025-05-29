package sdacademy.auctionsiteproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import sdacademy.auctionsiteproject.entity.User;
import sdacademy.auctionsiteproject.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    public User createUser(User user)
    {
        User newUser = new User();

        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setAccountName(user.getAccountName());
        newUser.setCity(user.getCity());
        newUser.setProvince(user.getProvince());
        newUser.setAddress(user.getAddress());
        newUser.setDateOfAccountCreation(user.getDateOfAccountCreation());
        newUser.setAccountStatus(user.getAccountStatus());
        newUser.setType(user.getType());

        return userRepository.save(newUser);
    }


    public User getUserByAccountName(String accountName)
    {
        return userRepository.findByAccountName(accountName).
                orElseThrow(() -> new RuntimeException("User with account name " + accountName + " not found!"));
    }
}
