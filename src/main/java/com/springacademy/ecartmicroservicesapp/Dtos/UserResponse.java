package com.springacademy.ecartmicroservicesapp.Dtos;

import com.springacademy.ecartmicroservicesapp.model.UserRole;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserResponseDto
{
    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private String phonenumber;
    private UserRole userRole;
    private AddressDTO address;
}
