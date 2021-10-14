package br.com.zupacademy.adriano.mercadolivre.compra;

import br.com.zupacademy.adriano.mercadolivre.produto.Produto;
import br.com.zupacademy.adriano.mercadolivre.transacao.StatusTransacao;
import br.com.zupacademy.adriano.mercadolivre.transacao.Transacao;
import br.com.zupacademy.adriano.mercadolivre.usuario.Usuario;
import org.springframework.util.Assert;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "compras")
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Min(1)
    @Column(nullable = false)
    private Integer quantidade;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FormaPagamento formaPagamento;

    @NotNull
    @Positive
    @Column(nullable = false)
    private BigDecimal valor;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    private LocalDateTime dataCriacao;


    @NotNull
    @Valid
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Produto produto;

    @NotNull @Valid
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;

    @Valid
    @OneToMany(mappedBy = "compra", cascade = {CascadeType.ALL})
    private Set<Transacao> transacoes;

    @Deprecated
    public Compra() {
    }

    public Compra(@NotNull @Min(1) Integer quantidade,
                  @NotNull FormaPagamento formaPagamento,
                  @NotNull @Positive BigDecimal valor,
                  @NotNull @Valid Produto produto,
                  @NotNull @Valid Usuario usuario) {

        this.quantidade = quantidade;
        this.formaPagamento = formaPagamento;
        this.valor = valor;
        this.produto = produto;
        this.usuario = usuario;

        this.status = Status.INICIADA;
        this.dataCriacao = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Produto getProduto() {
        return produto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public String gerarUrlDeRetorno(UriComponentsBuilder uriComponentsBuilder) {
        return formaPagamento.getGatewayPagamento().criarUrlDeRetorno(this,uriComponentsBuilder);
    }

    public void adicionarTransacao(Transacao transacao) {
        boolean ehCompraIniciada = this.status == Status.INICIADA;
        Assert.isTrue(ehCompraIniciada, "Compra já está concluída");

        boolean jaEstaCadastrada = !this.transacoes.contains(transacao);
        Assert.isTrue(jaEstaCadastrada, "Esta transação já está salva no banco de dados");

        this.transacoes.add(transacao);
    }

    public void concluirCompra(Transacao transacao) {
        boolean ehTransacaoComSucesso = transacao.getStatusTransacao() == StatusTransacao.SUCESSO && this.status == Status.INICIADA;
        Assert.isTrue(ehTransacaoComSucesso,"Não é possível concluir compra com erro");
        this.status = Status.CONCLUIDA;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Compra compra = (Compra) o;
        return id.equals(compra.id) && formaPagamento == compra.formaPagamento;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, formaPagamento);
    }
}
