package br.com.zupacademy.adriano.mercadolivre.transacao;

import br.com.zupacademy.adriano.mercadolivre.compra.Compra;
import br.com.zupacademy.adriano.mercadolivre.validacoes.ExisteId;
import br.com.zupacademy.adriano.mercadolivre.validacoes.StatusTransacaoValido;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class TransacaoRequest {
    @NotNull
    @ExisteId(classeDaEntidade = Compra.class, nomeDoCampo = "id")
    private Long compraId;

    @NotNull
    private Long pagamentoId;

    @NotBlank
    @StatusTransacaoValido
    private String status;

    public TransacaoRequest(@NotNull Long compraId,
                            @NotNull Long pagamentoId,
                            @NotBlank String status) {
        Assert.isTrue(StatusTransacao.validarStatus(status), "O status da transação deve ser um valor válido. Ex: 'SUCESSO', 'ERRO', '0' ou '1'");
        Assert.notNull(pagamentoId, "O id do pagamento não pode ser nulo");
        Assert.notNull(compraId, "O id da compra não pode ser nulo");
        this.compraId = compraId;
        this.pagamentoId = pagamentoId;
        this.status = status;
    }

    public Transacao toModel(EntityManager entityManager) {
        Compra compra = entityManager.find(Compra.class, compraId);
        StatusTransacao statusTransacao = List.of("SUCESSO", "1").contains(status)? StatusTransacao.SUCESSO:StatusTransacao.ERRO;
        Assert.notNull(compra, "Compra não encontrada");
        return new Transacao(compra, pagamentoId, statusTransacao);
    }
}
