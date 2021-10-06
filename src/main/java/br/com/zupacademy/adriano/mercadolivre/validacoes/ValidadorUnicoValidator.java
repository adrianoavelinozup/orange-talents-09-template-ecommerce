package br.com.zupacademy.adriano.mercadolivre.validacoes;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ValidadorUnicoValidator implements ConstraintValidator<ValorUnico, Object> {
    private String nomeDoCampo;
    private Class<?> classeDaEntidade;

    private final EntityManager entityManager;

    public ValidadorUnicoValidator(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void initialize(ValorUnico constraintAnnotation) {
        nomeDoCampo = constraintAnnotation.nomeDoCampo();
        classeDaEntidade = constraintAnnotation.classeDaEntidade();
    }

    @Override
    public boolean isValid(Object valorDoNomeDoCampo, ConstraintValidatorContext constraintValidatorContext) {
        String jpql = "SELECT e FROM " + classeDaEntidade.getName() + " e WHERE " + nomeDoCampo + " = :pValorDoNomeDoCampo";

        Query query = entityManager.createQuery(jpql);
        query.setParameter("pValorDoNomeDoCampo", valorDoNomeDoCampo);
        List resultList = query.getResultList();

        Assert.state(resultList.size() <=1, "Foi encontrado mais de um "+ classeDaEntidade +" com o atributo "+ nomeDoCampo +" = "+ valorDoNomeDoCampo);

        return resultList.isEmpty();
    }
}
