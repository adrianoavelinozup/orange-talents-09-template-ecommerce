package br.com.zupacademy.adriano.mercadolivre.validacoes;

import br.com.zupacademy.adriano.mercadolivre.transacao.StatusTransacao;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StatusTransacaoValidoValidator implements ConstraintValidator<StatusTransacaoValido, Object> {
    @Override
    public boolean isValid(Object valor, ConstraintValidatorContext constraintValidatorContext) {
        String valorDoStatus = (String) valor;
        return StatusTransacao.validarStatus(valorDoStatus);
    }
}
