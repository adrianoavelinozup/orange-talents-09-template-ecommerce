package br.com.zupacademy.adriano.mercadolivre.produto;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotBlank;

public class CaracteristicaProdutoRequest {
    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

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
