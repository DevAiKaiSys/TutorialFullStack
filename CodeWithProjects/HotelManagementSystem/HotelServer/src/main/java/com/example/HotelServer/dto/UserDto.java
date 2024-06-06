package com.example.HotelServer.dto;

import com.example.HotelServer.enums.UserRole;
import lombok.Data;

@Data
public class UserDto {

    private Long id;
    private String email;
    private String name;
    private UserRole userRole;

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
}
