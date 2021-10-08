package br.com.zupacademy.adriano.mercadolivre.validacao;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {ElementosUnicosValidator.class})
@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ElementosUnicos {
    String message() default "não deve conter valores repetidos";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    String nomeDoCampo();

    Class<?> classeDaEntidade();
}
