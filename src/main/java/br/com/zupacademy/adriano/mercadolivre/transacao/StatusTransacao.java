package br.com.zupacademy.adriano.mercadolivre.transacao;

import java.util.List;

public enum StatusTransacao {
    SUCESSO, ERRO;

    public static final List<String> VALORES_VALIDOS = List.of("SUCESSO", "ERRO", "0", "1");

    public static boolean validarStatus(String status) {
        return VALORES_VALIDOS.contains(status);
    }
}
