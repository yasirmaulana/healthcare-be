package io.github.yasirmaulana.healthcare.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategy.class)
@Builder
public class WebResponse<T> {
    private String status;
    private String code;
    private String message;
    private T data;
}
