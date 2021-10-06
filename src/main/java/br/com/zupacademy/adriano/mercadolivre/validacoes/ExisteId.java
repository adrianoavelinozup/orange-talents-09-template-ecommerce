package br.com.zupacademy.adriano.mercadolivre.validacoes;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Verifica se um identificador, não nulo, existe no banco de dados
 */
@Documented
@Constraint(validatedBy = {ExisteIdValidator.class})
@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExisteId {
    String message() default "não encontrado";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    String nomeDoCampo();

    Class<?> classeDaEntidade();
}
