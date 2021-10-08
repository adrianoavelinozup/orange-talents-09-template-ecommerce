package br.com.zupacademy.adriano.mercadolivre.validacao;

import br.com.zupacademy.adriano.mercadolivre.produto.CaracteristicaProdutoRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ElementosUnicosValidator implements ConstraintValidator<ElementosUnicos, Object> {
    private String nomeDoCampo;
    private Class<?> classeDaEntidade;

    @Override
    public void initialize(ElementosUnicos constraintAnnotation) {
        nomeDoCampo = constraintAnnotation.nomeDoCampo();
        classeDaEntidade = constraintAnnotation.classeDaEntidade();
    }

    @Override
    public boolean isValid(Object valorDoNomeDoCampo, ConstraintValidatorContext constraintValidatorContext) {

        List<CaracteristicaProdutoRequest> caracteristicas = (List<CaracteristicaProdutoRequest>) valorDoNomeDoCampo;
        List<String> nomesDasCaracteristica = new ArrayList<>();

        caracteristicas.forEach( e -> {
           if (!nomesDasCaracteristica.contains(e.getNome())) {
               nomesDasCaracteristica.add(e.getNome());
           }
        });


        return nomesDasCaracteristica.size() == caracteristicas.size();
    }
}
