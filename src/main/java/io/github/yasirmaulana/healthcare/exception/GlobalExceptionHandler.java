package io.github.yasirmaulana.healthcare.exception;

import io.github.yasirmaulana.healthcare.dto.WebResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
    public ResponseEntity<WebResponse<Void>> handleNotFoundException(ChangeSetPersister.NotFoundException ex) {
        WebResponse<Void> response = WebResponse.<Void>builder()
                .status("ERROR")
                .code("404")
                .message("Data not found: " + ex.getMessage())
                .data(null)
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        String errorMessage = "Duplicate SKU value detected. Please use a unique SKU for each product.";

        if (ex.getCause() != null && ex.getCause().getCause() != null) {
            String detailedError = ex.getCause().getCause().getMessage();
            if (detailedError.contains("duplicate key value violates unique constraint")) {
                errorMessage = "SKU already exists. Please choose a unique SKU.";
            }
        }

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<WebResponse<Void>> handleValidationException(MethodArgumentNotValidException ex) {
        String errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .reduce("", (acc, error) -> acc + error + "; ");

        WebResponse<Void> response = WebResponse.<Void>builder()
                .status("ERROR")
                .code("400")
                .message("Validation failed: " + errors)
                .data(null)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<WebResponse<Void>> handleGeneralException(Exception ex) {
        WebResponse<Void> response = WebResponse.<Void>builder()
                .status("ERROR")
                .code("500")
                .message("An error occured: " + ex.getMessage())
                .data(null)
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
