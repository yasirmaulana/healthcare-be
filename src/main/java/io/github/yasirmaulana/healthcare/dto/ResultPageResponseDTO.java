package io.github.yasirmaulana.healthcare.dto;

import lombok.Data;

import java.util.List;


@Data
public class ResultPageResponseDTO<T> {

    private List<T> result;
    private Integer page;
    private Long element;
}
