package br.com.zupacademy.adriano.mercadolivre.produto;

import br.com.zupacademy.adriano.mercadolivre.categoria.Categoria;
import br.com.zupacademy.adriano.mercadolivre.usuario.Usuario;
import br.com.zupacademy.adriano.mercadolivre.validacao.ElementosUnicos;
import br.com.zupacademy.adriano.mercadolivre.validacoes.ExisteId;
import br.com.zupacademy.adriano.mercadolivre.validacoes.ValorUnico;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ProdutoRequest {

    @NotBlank
    @ValorUnico(classeDaEntidade = Produto.class, nomeDoCampo = "nome")
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
    @ElementosUnicos(nomeDoCampo = "caracteristicas", message = "não deve conter característica com nome repetido. Escolha nomes diferentes")
    private List<@Valid CaracteristicaProdutoRequest> caracteristicas = new ArrayList<>();

    @NotNull
    @ExisteId(classeDaEntidade = Categoria.class, nomeDoCampo = "id")
    private Long categoriaId;

    public ProdutoRequest(@NotBlank String nome,
                          @NotNull @Min(1) BigDecimal valor,
                          @NotNull @Min(0) Integer quantidadeDisponivel,
                          @NotBlank @Length(max = 1000)String descricao,
                          @NotNull @Size(min = 3) List<@Valid CaracteristicaProdutoRequest> caracteristicas,
                          @NotNull Long categoriaId) {
        this.nome = nome;
        this.valor = valor;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.caracteristicas = caracteristicas;
        this.descricao = descricao;
        this.categoriaId = categoriaId;
    }

    public @NotNull @Size(min = 3) List<@Valid CaracteristicaProdutoRequest> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(List<CaracteristicaProdutoRequest> caracteristicas) {
        this.caracteristicas = caracteristicas;
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

    public Produto toModel(EntityManager entityManager, Usuario dono) {
        Categoria categoria = entityManager.find(Categoria.class, this.categoriaId);
        Produto produto = null;

        if (categoria != null) {
             produto = new Produto(this.nome, this.valor, this.quantidadeDisponivel, this.descricao, categoria, this.caracteristicas, dono);
            return produto;
        }
        Assert.notNull(categoria, "Categoria não pode ser nula");
        return produto;
    }
}
