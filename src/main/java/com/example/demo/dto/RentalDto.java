package com.example.demo.dto;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
public class RentalDto {

    private Long id;
    private String name;
    private int surface;
    private int price;
    private MultipartFile picture;
    private String description;
    private Long ownerId;

    private Date created_at;

    private Date updated_at;
}
