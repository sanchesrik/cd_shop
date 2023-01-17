package com.example.demo.services.requests;

import lombok.Data;

@Data
public class StaffCreateRequest {
    private String firstname;
    private String lastname;
    private Integer position_id;
}
