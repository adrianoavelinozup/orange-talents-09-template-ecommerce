package br.com.zupacademy.adriano.mercadolivre.transacao;

import br.com.zupacademy.adriano.mercadolivre.compra.Compra;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "transacoes")
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Valid
    @ManyToOne(fetch = FetchType.LAZY)
    private Compra compra;

    @NotNull
    @Column(nullable = false)
    private Long pagamentoId;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusTransacao statusTransacao;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime dataCriacao;

    @Deprecated
    public Transacao() {
    }

    public Transacao(Compra compra, Long pagamentoId, StatusTransacao statusTransacao) {
        this.compra = compra;
        this.pagamentoId = pagamentoId;
        this.statusTransacao = statusTransacao;
        dataCriacao = LocalDateTime.now();
    }

    public Long getPagamentoId() {
        return pagamentoId;
    }

    public StatusTransacao getStatusTransacao() {
        return statusTransacao;
    }

    public Compra getCompra() {
        return compra;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transacao transacao = (Transacao) o;
        return compra.equals(transacao.compra) && pagamentoId.equals(transacao.pagamentoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(compra, pagamentoId);
    }

    public boolean isTransacaoComSucesso() {
        return StatusTransacao.SUCESSO.equals(this.statusTransacao);
   }

}
