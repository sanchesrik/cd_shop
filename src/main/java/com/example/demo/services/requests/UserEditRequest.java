package com.example.demo.services.requests;

import lombok.Data;

@Data
public class UserEditRequest {
    private Integer id;
    private String firstname;
    private String lastname;
}
