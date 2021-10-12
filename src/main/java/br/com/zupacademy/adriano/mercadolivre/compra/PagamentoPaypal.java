package br.com.zupacademy.adriano.mercadolivre.compra;

import org.springframework.stereotype.Component;

@Component
public class PagamentoPaypal implements GatewayPagamento {
    private String urlPagamento = "paypal.com?buyerId=%d&redirectUrl=%s";
    private String urlRedirecionamento = "localhost:8080/compras/%d";

    @Override
    public String encaminharPagamento(Compra compra) {
        return String.format(urlPagamento, compra.getId(), String.format(urlRedirecionamento, compra.getId()));
    }
}
