package com.springacademy.ecartmicroservicesapp.repository;

import com.springacademy.ecartmicroservicesapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Custom query methods can be defined here if needed
    // For example, findByFirstname, findByLastname, etc.
}
