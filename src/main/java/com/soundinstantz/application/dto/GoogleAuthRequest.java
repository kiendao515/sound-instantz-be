package com.soundinstantz.application.dto;

import lombok.Data;

@Data
public class GoogleAuthRequest {
    private String email;
    private String name;
    private String picture;
    private String sub;  // Google's unique identifier
}
