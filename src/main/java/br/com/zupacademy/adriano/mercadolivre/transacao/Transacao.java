package br.com.zupacademy.adriano.mercadolivre.transacao;

import br.com.zupacademy.adriano.mercadolivre.compra.Compra;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "transacoes")
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String transacaoId;

    @NotNull
    @Valid
    @ManyToOne(fetch = FetchType.LAZY)
    private Compra compra;

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

    public Transacao(@NotNull StatusTransacao statusTransacao,
                     @NotBlank String transacaoId,
                     @NotNull Compra compra) {
        this.statusTransacao = statusTransacao;
        this.transacaoId = transacaoId;
        this.compra = compra;
        dataCriacao = LocalDateTime.now();
    }

    public StatusTransacao getStatusTransacao() {
        return statusTransacao;
    }

    public boolean concluidaComSucesso() {
        return statusTransacao.equals(StatusTransacao.SUCESSO);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transacao transacao = (Transacao) o;
        return transacaoId.equals(transacao.transacaoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transacaoId);
    }
}
