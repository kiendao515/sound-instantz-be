package com.soundinstantz.util;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApiResponse<T> {
    private T data;

    public ApiResponse(T data) {
        this.data = data;
    }
}
