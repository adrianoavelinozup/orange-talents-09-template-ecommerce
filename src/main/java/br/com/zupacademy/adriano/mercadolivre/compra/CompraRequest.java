package br.com.zupacademy.adriano.mercadolivre.compra;

import br.com.zupacademy.adriano.mercadolivre.produto.Produto;
import br.com.zupacademy.adriano.mercadolivre.usuario.Usuario;
import br.com.zupacademy.adriano.mercadolivre.validacoes.ExisteId;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

public class CompraRequest {
    @NotNull
    @Min(1)
    private Integer quantidade;

    @NotNull
    @ExisteId(classeDaEntidade = Produto.class, nomeDoCampo = "id")
    private Long produtoId;

    @NotNull
    private FormaPagamento formaPagamento;

    public CompraRequest(@NotNull @Min(1) Integer quantidade,
                         @NotNull Long produtoId,
                         @NotNull FormaPagamento formaPagamento) {
        Assert.notNull(quantidade, "A quantidade não pode ser nula");
        Assert.notNull(produtoId, "O id do produto não pode ser nulo");
        this.quantidade = quantidade;
        this.produtoId = produtoId;
        this.formaPagamento = formaPagamento;
    }

    public Compra toModel(@Null @NotNull EntityManager entityManager, @NotNull @Valid Usuario usuario) {
        Produto produto = entityManager.find(Produto.class, produtoId);
        Assert.notNull(produto, "Produto não pode ser nulo");
        Assert.notNull(usuario, "Usuário não pode ser nulo");
        return new Compra(this.quantidade,
                this.formaPagamento,
                produto.getValor(),
                produto,
                usuario);
    }
}
