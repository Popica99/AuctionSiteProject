package sdacademy.auctionsiteproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sdacademy.auctionsiteproject.entity.User;
import sdacademy.auctionsiteproject.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user)
    {
        try {
            User newUser = userService.createUser(user);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        } catch (RuntimeException e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{accountName}")
    public ResponseEntity<User> getUserByAccountName(@PathVariable String accountName)
    {
        try {
            User userByName = userService.getUserByAccountName(accountName);
            return new ResponseEntity<>(userByName, HttpStatus.OK);
        } catch (RuntimeException e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{userName}")
    public ResponseEntity<String> updateUser(@PathVariable String userName, @RequestBody User updatedUser)
    {
        if (userService.updateUser(userName, updatedUser).isPresent())
            return ResponseEntity.ok("User " + updatedUser.getAccountName() + " updated successfully!");
        else
            return ResponseEntity.status(404).body("User with account name " + userName + " not found!");
    }

    @DeleteMapping("/{accountName}")
    public ResponseEntity<String> deleteUserByAccountName(@PathVariable String accountName)
    {
        try{
            String message = userService.deleteUserByAccountName(accountName);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (RuntimeException e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
