package br.com.zupacademy.adriano.mercadolivre.validacao;

import br.com.zupacademy.adriano.mercadolivre.produto.CaracteristicaProdutoRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ElementosUnicosValidator implements ConstraintValidator<ElementosUnicos, Object> {
    private String elementos;

    @Override
    public void initialize(ElementosUnicos constraintAnnotation) {
        elementos = constraintAnnotation.nomeDoCampo();
    }

    @Override
    public boolean isValid(Object elementos, ConstraintValidatorContext constraintValidatorContext) {

        List<CaracteristicaProdutoRequest> caracteristicas = (List<CaracteristicaProdutoRequest>) elementos;
        List<String> nomesDasCaracteristica = new ArrayList<>();

        caracteristicas.forEach( e -> {
           if (!nomesDasCaracteristica.contains(e.getNome())) {
               nomesDasCaracteristica.add(e.getNome());
           }
        });

        return nomesDasCaracteristica.size() == caracteristicas.size();
    }
}
