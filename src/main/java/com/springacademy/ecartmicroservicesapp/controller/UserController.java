package com.springacademy.ecartmicroservicesapp.controller;

import com.springacademy.ecartmicroservicesapp.Dtos.UserRequest;
import com.springacademy.ecartmicroservicesapp.Dtos.UserResponse;
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
    public ResponseEntity<List<UserResponse>> getAllUsers()
    {
        return new ResponseEntity<>(userService.fetchAllUsers(), HttpStatus.OK); //return userService.fetchAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable("id") Long id)
    {

        return userService.fetchSingleUser(id)
                .map(user -> (ResponseEntity<UserResponse>) new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<String> createUsers(@RequestBody UserRequest userRequest)
    {
       userService.AddUsers(userRequest);
        //userService.AddUsers(userRequestDto);
        //return userService.AddUsers(userRequestDto);
        //return new ResponseEntity<>(userService.AddUsers(userRequestDto), HttpStatus.CREATED);
        //return new ResponseEntity<>(HttpStatus.CREATED);
        //return new ResponseEntity<>("User Created Successfully", HttpStatus.CREATED);
       return ResponseEntity.ok("User Created Successfully"); //return "User Created Successfully";
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable ("id") Long id,
                                             @RequestBody UserRequest updateduser)
    {
        //userService.updateUser(id, updateduser);
        //return new ResponseEntity<>(HttpStatus.OK);
        //return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
        //return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
    {
        boolean isUpdated = userService.updateUser(id, updateduser);
        if (!isUpdated) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
        }
        return ResponseEntity.ok("User updated successfully"); //return "User Created Successfully";
    }

}}
