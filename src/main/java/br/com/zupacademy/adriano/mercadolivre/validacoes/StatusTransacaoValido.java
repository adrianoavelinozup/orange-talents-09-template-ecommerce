package br.com.zupacademy.adriano.mercadolivre.validacoes;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Verifica se um status é SUCESSO, ERRO, String 0 ou String 1
 */
@Documented
@Constraint(validatedBy = {StatusTransacaoValidoValidator.class})
@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface StatusTransacaoValido {
    String message() default "inválido. O status deve ser 'SUCESSO', 'ERRO', '0' ou '1'";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
