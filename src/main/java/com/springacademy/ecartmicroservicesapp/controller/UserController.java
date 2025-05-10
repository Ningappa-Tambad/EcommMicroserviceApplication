package com.springacademy.ecartmicroservicesapp.controller;

import com.springacademy.ecartmicroservicesapp.services.UserService;
import com.springacademy.ecartmicroservicesapp.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController
{

private UserService userService;

public UserController(UserService userService) {
        this.userService = userService;
    }
    //@GetMapping("/api/users")
    @RequestMapping(method = RequestMethod.GET) // @ equestMapping("/api/users")
    public ResponseEntity<List<User>> getAllUsers()
    {
        return new ResponseEntity<>(userService.fetchAllUsers(), HttpStatus.OK); //return userService.fetchAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id)
    {

        return userService.fetchSingleUser(id)
                .map(user -> (ResponseEntity<User>) new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<String> createUsers(@RequestBody User user)
    {
       userService.AddUsers(user);
       return ResponseEntity.ok("User Created Successfully"); //return "User Created Successfully";
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> createUsers(@PathVariable ("id") Long id,@RequestBody User updateduser)
    {
        boolean isUpdated = userService.updateUser(id, updateduser);
        if (!isUpdated) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
        }
        return ResponseEntity.ok("User updated successfully"); //return "User Created Successfully";
    }

}
