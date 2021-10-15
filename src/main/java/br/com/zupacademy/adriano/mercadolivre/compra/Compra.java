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
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

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
    @NotNull
    @OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
    private Set<Transacao> transacoes = new HashSet<>();

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

    public void adicionarTransacao(@Valid Transacao transacao) {
        Assert.isTrue(!this.transacoes.contains(transacao), "Esta transação já está cadastrada ");
        Assert.isTrue(transacoesConcluidasComSucesso().isEmpty(),"Esta compra já foi concluída com sucesso. Você não pode alterar uma compra concluída");

        this.transacoes.add(transacao);
    }

    private Set<Transacao> transacoesConcluidasComSucesso() {
        Set<Transacao> transacoesConcluidasComSucesso = this.transacoes.stream()
                .filter(Transacao::concluidaComSucesso)
                .collect(Collectors.toSet());

        Assert.isTrue(transacoesConcluidasComSucesso.size() <= 1,"Uma transação concluída com sucesso na compra "+this.id);

        return transacoesConcluidasComSucesso;
    }

    public void concluir(Transacao transacao) {
        if (transacao.getStatusTransacao().equals(StatusTransacao.SUCESSO)) {
            this.status = Status.CONCLUIDA;
        }
    }

    public boolean processadaComSucesso() {
        return !transacoesConcluidasComSucesso().isEmpty();
    }
}
