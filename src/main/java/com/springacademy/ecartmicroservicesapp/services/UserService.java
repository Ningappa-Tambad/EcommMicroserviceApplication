package com.springacademy.ecartmicroservicesapp.services;

import com.springacademy.ecartmicroservicesapp.Dtos.AddressDTO;
import com.springacademy.ecartmicroservicesapp.Dtos.UserRequest;
import com.springacademy.ecartmicroservicesapp.Dtos.UserResponse;
import com.springacademy.ecartmicroservicesapp.model.Address;
import com.springacademy.ecartmicroservicesapp.model.User;
import com.springacademy.ecartmicroservicesapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService
{

    private UserRepository userRepository;

    private List<User> usersList=new ArrayList<>();
    private Long nextId=1L;


    public List<UserResponse> fetchAllUsers()
    {

       return userRepository.findAll().stream()
                .map(this::mapToUserResponseDto)
                .collect(Collectors.toList());
    }


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public void AddUsers(UserRequest userRequest)
    {
//        user.setId(nextId++);
//        usersList.add(user);
        User user = new User();
        UpdateUserFromRequestDto(user,userRequest);
      userRepository.save(user);

    }



    public Optional<UserResponse> fetchSingleUser(Long id) {
//        return usersList.stream()
//                .filter(user -> user.getId().equals(id)).findFirst();

        return userRepository.findById(id)
                .map(this::mapToUserResponseDto);
    }


    public boolean updateUser(Long id, UserRequest updatedUser) {

        return usersList.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .map(existingUser -> {
                 UpdateUserFromRequestDto(existingUser, updatedUser);
                    userRepository.save(existingUser);

                    return true;
                })
                .orElse(false);
    }

    private void UpdateUserFromRequestDto(User user, UserRequest userRequest)
    {

        user.setFirstname(userRequest.getFirstname());
        user.setLastname(userRequest.getLastname());
        user.setEmail(userRequest.getEmail());
        user.setPhonenumber(userRequest.getPhonenumber());
        user.setUserRole(userRequest.getUserRole());
        if (userRequest.getAddress() != null)
        {
            Address address = new Address();
            address.setStreet(userRequest.getAddress().getStreet());
            address.setCity(userRequest.getAddress().getCity());
            address.setState(userRequest.getAddress().getState());
            address.setCountry(userRequest.getAddress().getCountry());
            address.setZipcode(userRequest.getAddress().getZipcode());
            user.setAddress(address);
        }

    }


        public UserResponse mapToUserResponseDto (User user)
        {
            UserResponse response = new UserResponse();
            response.setId(String.valueOf(user.getId()));
            response.setFirstname(user.getFirstname());
            response.setLastname(user.getLastname());
            response.setEmail(user.getEmail());
            response.setPhonenumber(user.getPhonenumber());

            if (user.getAddress() != null) {
                AddressDTO addressDTO = new AddressDTO();
                addressDTO.setCity(user.getAddress().getCity());
                addressDTO.setCountry(user.getAddress().getCountry());
                addressDTO.setState(user.getAddress().getState());
                addressDTO.setStreet(user.getAddress().getStreet());
                addressDTO.setZipcode(user.getAddress().getZipcode());
                response.setAddress(addressDTO);
            }

            return response;
        }
    }

