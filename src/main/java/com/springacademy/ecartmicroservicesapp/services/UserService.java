package com.springacademy.ecartmicroservicesapp.services;

import com.springacademy.ecartmicroservicesapp.model.User;
import com.springacademy.ecartmicroservicesapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService
{

    private UserRepository userRepository;

    private List<User> usersList=new ArrayList<>();
    private Long nextId=1L;


    public List<User> fetchAllUsers()
    {

       return userRepository.findAll();
    }


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public void AddUsers(User user)
    {
//        user.setId(nextId++);
//        usersList.add(user);
      userRepository.save(user);

    }


    public Optional<User> fetchSingleUser(Long id) {
//        return usersList.stream()
//                .filter(user -> user.getId().equals(id)).findFirst();

        return userRepository.findById(id);
    }


    public boolean updateUser(Long id, User updatedUser) {

        return usersList.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .map(user -> {
                    user.setFirstname(updatedUser.getFirstname());
                    user.setLastname(updatedUser.getLastname());
                    userRepository.save(user);

                    return true;
                })
                .orElse(false);
    }
}
