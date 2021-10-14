package br.com.zupacademy.adriano.mercadolivre.validacao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErroDeValidacaoHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErroDto> handle(MethodArgumentNotValidException exception) {
        List<ErroDto> errosDto = new ArrayList<>();

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        List<ObjectError> globalErrors = exception.getBindingResult().getGlobalErrors();
        fieldErrors.forEach(e -> {
            String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            ErroDto erro = new ErroDto(e.getField(), mensagem);
            errosDto.add(erro);
        });

        globalErrors.forEach(e -> {
            ErroDto erro = new ErroDto("error", e.getDefaultMessage());
            errosDto.add(erro);
        });

        return errosDto;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroDto handleConverterError(HttpMessageNotReadableException ex, WebRequest request) {
        String message = ex.getMessage();
        if (message.contains("br.com.zupacademy.adriano.mercadolivre.compra.FormaPagamento")) {
            return new ErroDto("formaPagamento", "Valor inválido. O valor deve ser: PAYPAL ou PAGSEGURO");
        }
        return new ErroDto("error", ex.getRootCause().getLocalizedMessage());
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<?> handleResposeEntityExceptions(ResponseStatusException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErroDto("quantidade", ex.getReason()));
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<?> handleResposeEntityExceptions(BindException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErroDto("quantidade", ex.getGlobalErrors().get(0).getDefaultMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleResposeEntityExceptions(IllegalArgumentException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErroDto("erro", ex.getMessage()));
    }

}