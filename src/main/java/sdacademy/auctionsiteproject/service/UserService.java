package sdacademy.auctionsiteproject.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(User user)
    {
        User newUser = new User();

        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
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
            Roles newRole = new Roles("user");
            roleRepository.save(newRole);
            rolesList.add(newRole);
        }
        else
        {
            rolesList.add(checkRole);
        }


        newUser.setRoles(rolesList);
        return userRepository.save(newUser);
    }

    public User createAdmin(User user)
    {
        User newUser = new User();

        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setAccountName(user.getAccountName());
        newUser.setCity(user.getCity());
        newUser.setProvince(user.getProvince());
        newUser.setAddress(user.getAddress());
        newUser.setDateOfAccountCreation(user.getDateOfAccountCreation());
        newUser.setAccountStatus(user.getAccountStatus());
        newUser.setType(user.getType());

        Roles checkRole = roleRepository.findByName("admin");

        List<Roles> rolesList = new ArrayList<>();
        if (checkRole == null)
        {
            Roles newRole = new Roles("admin");
            roleRepository.save(newRole);
            rolesList.add(newRole);
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
        System.out.println("Caut user cu accountName = " + accountName);
        return userRepository.findByAccountName(accountName).
                orElseThrow(() -> new UserNotFoundException("User not found!"));
    }

    @Transactional
    public void clearRoles(String name) {
        User user = getUserByAccountName(name);
        user.getRoles().clear();
        userRepository.save(user);
    }

    @Transactional
    public String deleteUserByAccountName(String name)
    {
        clearRoles(name);
        User user = getUserByAccountName(name);
        userRepository.delete(user);
        return "User was deleted successfully";
    }

    public Optional<User> updateUser (String userName, User updatedUser)
    {
        return userRepository.findByAccountName(userName)
                .map(user ->
                {
                    user.setEmail(updatedUser.getEmail());
                    user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
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
