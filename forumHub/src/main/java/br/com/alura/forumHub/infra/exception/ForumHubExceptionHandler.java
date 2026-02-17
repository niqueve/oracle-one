package br.com.alura.forumHub.infra.exception;

import br.com.alura.forumHub.domain.ForumHubValidationException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ForumHubExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handler404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handler400 (MethodArgumentNotValidException ex) {
        var errors = ex.getFieldErrors().stream().map(DataValidationErros::new).toList();
        return ResponseEntity.badRequest().body(errors);
    }

    private record DataValidationErros(String field, String message) {
        public DataValidationErros(FieldError fieldError) {
            this(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }

    @ExceptionHandler(ForumHubValidationException.class)
    public ResponseEntity handlerBusinessRules(ForumHubValidationException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
