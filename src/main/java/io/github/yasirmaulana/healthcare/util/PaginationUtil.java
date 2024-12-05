package io.github.yasirmaulana.healthcare.util;

import io.github.yasirmaulana.healthcare.dto.ResultPageResponseDTO;
import org.springframework.data.domain.Sort;

import java.util.List;


public class PaginationUtil {

    public static Sort.Direction getSortBy(String sortBy) {
        if (sortBy.equalsIgnoreCase("asc")) {
            return Sort.Direction.ASC;
        } else {
            return Sort.Direction.DESC;
        }
    }

    public static <T> ResultPageResponseDTO<T> createResultPageDTO(List<T> dtos, Long totalElement, Integer pages) {
        ResultPageResponseDTO<T> result = new ResultPageResponseDTO<>();
        result.setPage(pages);
        result.setElement(totalElement);
        result.setResult(dtos);
        return result;
    }


}
