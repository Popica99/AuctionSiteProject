package sdacademy.auctionsiteproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import sdacademy.auctionsiteproject.entity.Roles;
import sdacademy.auctionsiteproject.entity.User;
import sdacademy.auctionsiteproject.exceptions.UserNotFoundException;
import sdacademy.auctionsiteproject.repository.RoleRepository;
import sdacademy.auctionsiteproject.repository.UserRepository;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public RoleRepository roleRepository;

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

        Roles checkRole = roleRepository.findByName("user");

        List<Roles> rolesList = new ArrayList<>();
        if (checkRole == null)
        {
            Roles roles = new Roles("user");
            rolesList.add(roles);
        }
        else
        {
            rolesList.add(checkRole);
        }


        newUser.setRoles(rolesList);
        return userRepository.save(newUser);
    }

    public User getUserByAccountName(String accountName)
    {
        return userRepository.findByAccountName(accountName).
                orElseThrow(() -> new UserNotFoundException("User not found!"));
    }

    public String deleteUserByAccountName(String name)
    {
        userRepository.delete(getUserByAccountName(name));
        return "User was deleted successfully";
    }

    public Optional<User> updateUser (String userName, User updatedUser)
    {
        return userRepository.findByAccountName(userName)
                .map(user ->
                {
                    user.setEmail(updatedUser.getEmail());
                    user.setPassword(updatedUser.getPassword());
                    user.setAccountName(updatedUser.getAccountName());
                    user.setProvince(updatedUser.getProvince());
                    user.setCity(updatedUser.getCity());
                    user.setAddress(updatedUser.getAddress());
                    user.setDateOfAccountCreation(updatedUser.getDateOfAccountCreation());
                    user.setAccountStatus(updatedUser.getAccountStatus());
                    user.setType(updatedUser.getType());
                    user.setRoles(updatedUser.getRoles());
                    user.setAuctions(updatedUser.getAuctions());
                    return userRepository.save(user);
                });
    }
}
