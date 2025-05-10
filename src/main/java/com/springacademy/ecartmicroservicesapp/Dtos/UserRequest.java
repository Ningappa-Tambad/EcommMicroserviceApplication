package com.springacademy.ecartmicroservicesapp.Dtos;

import com.springacademy.ecartmicroservicesapp.model.UserRole;
import lombok.Data;

import java.util.Locale;


@Data
public class UserRequestDto
{
    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private String phonenumber;
    private UserRole userRole;


}
