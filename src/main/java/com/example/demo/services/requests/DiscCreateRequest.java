package com.example.demo.services.requests;

import com.example.demo.domain.Producer;
import lombok.Data;

import javax.validation.constraints.*;
import java.util.List;

@Data
public class DiscCreateRequest {
    @NotNull
    @Size(min = 3, max = 50)
    private String name;

    @Min(value = 1000)
    @Max(value = 2023)
    private Integer year;

    @Min(value = 1)
    private Integer genre_id;

    @NotEmpty(message = "У диска должен быть указан продюсер")
    List<Integer> producer_id;

}
