package com.example.demo.services.requests;

import lombok.Data;

@Data
public class DiscEditRequest {
    private Integer id;
    private String name;
    private String country;
    private Integer year;
    private Integer genre_id;
    private Integer producer_id;
}
