package br.com.zupacademy.adriano.mercadolivre.compra;

public interface GatewayPagamento {
    String encaminharPagamento(Compra compra);
}
