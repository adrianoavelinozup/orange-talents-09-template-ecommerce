package br.com.zupacademy.adriano.mercadolivre.produto;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotBlank;

public class CaracteristicaProdutoRequest {
    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

//    public CaracteristicaProdutoRequest(@NotBlank String nome, @NotBlank String descricao) {
//        super();
//
//        Assert.isTrue(StringUtils.hasLength(nome),"O campo nome não pode estar em branco");
//        Assert.isTrue(StringUtils.hasLength(descricao),"O campo descrição não pode estar em branco");
//
//        this.nome = nome;
//        this.descricao = descricao;
//    }



//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        CaracteristicaProdutoRequest that = (CaracteristicaProdutoRequest) o;
//
//        return nome.equals(that.nome);
//    }
//
//    @Override
//    public int hashCode() {
//        return nome.hashCode();
//    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return "CaracteristicaProdutoRequest{" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
