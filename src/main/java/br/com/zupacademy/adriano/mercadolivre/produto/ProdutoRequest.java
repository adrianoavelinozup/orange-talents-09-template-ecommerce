package br.com.zupacademy.adriano.mercadolivre.produto;

import br.com.zupacademy.adriano.mercadolivre.categoria.Categoria;
import br.com.zupacademy.adriano.mercadolivre.validacoes.ExisteId;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ProdutoRequest {

    @NotBlank
    private String nome;

    @NotNull
    @Positive
    private BigDecimal valor;

    @NotNull
    @Min(0)
    private Integer quantidadeDisponivel;

    @NotBlank
    @Length(max = 1000)
    private String descricao;

    @NotNull
    @Size(min = 3)
    private Set<@Valid CaracteristicaProdutoRequest> caracteristicas = new HashSet<>();

    @NotNull
    @ExisteId(classeDaEntidade = Categoria.class, nomeDoCampo = "id")
    private Long categoriaId;

    public ProdutoRequest(@NotBlank String nome,
                          @NotNull @Min(1) BigDecimal valor,
                          @NotNull @Min(0) Integer quantidadeDisponivel,
                          @NotBlank @Length(max = 1000)String descricao,
                          Set<CaracteristicaProdutoRequest> caracteristicas,
                          @NotNull Long categoriaId) {
        this.nome = nome;
        this.valor = valor;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.caracteristicas = caracteristicas;
        this.descricao = descricao;
        this.categoriaId = categoriaId;
    }

    public Set<CaracteristicaProdutoRequest> getCaracteristicas() {
        return caracteristicas;
    }

    @Override
    public String toString() {
        return "ProdutoRequest{" +
                "nome='" + nome + '\'' +
                ", valor=" + valor +
                ", quantidadeDisponivel=" + quantidadeDisponivel +
                ", descricao='" + descricao + '\'' +
                ", caracteristicas=" + caracteristicas +
                ", categoriaId=" + categoriaId +
                '}';
    }
}
