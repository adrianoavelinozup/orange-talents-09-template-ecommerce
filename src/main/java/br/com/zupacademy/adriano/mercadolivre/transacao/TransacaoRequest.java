package br.com.zupacademy.adriano.mercadolivre.transacao;

import br.com.zupacademy.adriano.mercadolivre.compra.Compra;
import br.com.zupacademy.adriano.mercadolivre.validacoes.StatusTransacaoValido;
import org.springframework.util.Assert;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

public class TransacaoRequest {
    @NotBlank
    private String transacaoId;

    @NotBlank
    @StatusTransacaoValido
    private String status;

    public TransacaoRequest(@NotBlank String transacaoId,
                            @NotBlank String status) {
        Assert.isTrue(StatusTransacao.validarStatus(status), "O status da transação deve ser um valor válido. Ex: 'SUCESSO', 'ERRO', '0' ou '1'");
        Assert.notNull(transacaoId, "O id do pagamento não pode ser nulo");
        this.transacaoId = transacaoId;
        this.status = status;
    }

    public Transacao toModel(@Valid Compra compra) {
        StatusTransacao statusTransacao = List.of("SUCESSO", "1").contains(status)? StatusTransacao.SUCESSO: StatusTransacao.ERRO;
        Assert.notNull(compra, "Compra não encontrada");
//        return new Transacao(compra, pagamentoId, statusTransacao2);
        return new Transacao(statusTransacao, transacaoId, compra);
    }
}
