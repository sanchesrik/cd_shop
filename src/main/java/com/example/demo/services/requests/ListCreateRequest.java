package com.example.demo.services.requests;

import com.example.demo.domain.Action;
import lombok.Data;

@Data
public class ListCreateRequest {
    private Integer disc_id;
    private Integer user_id;
    private Integer staff_id;
    private Action action;
    private String was_created;
}
