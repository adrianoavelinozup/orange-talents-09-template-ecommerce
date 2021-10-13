package br.com.zupacademy.adriano.mercadolivre.compra;

import br.com.zupacademy.adriano.mercadolivre.produto.Produto;
import br.com.zupacademy.adriano.mercadolivre.usuario.Usuario;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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

    public Integer getQuantidade() {
        return quantidade;
    }

    public String gerarUrlDeRetorno(UriComponentsBuilder uriComponentsBuilder) {
        return formaPagamento.getGatewayPagamento().criarUrlDeRetorno(this,uriComponentsBuilder);
    }
}
