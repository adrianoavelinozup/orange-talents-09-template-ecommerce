package br.com.zupacademy.adriano.mercadolivre.compra;

public enum FormaPagamento {

    PAYPAL(new PagamentoPaypal()),
    PAGSEGURO(new PagamentoPagSeguro());

    private GatewayPagamento gatewayPagamento;

    FormaPagamento(GatewayPagamento gatewayPagamento) {
        this.gatewayPagamento = gatewayPagamento;
    }

    public GatewayPagamento getGatewayPagamento() {
        return gatewayPagamento;
    }
}
