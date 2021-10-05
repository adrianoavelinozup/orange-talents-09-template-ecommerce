package br.com.zupacademy.adriano.mercadolivre.validacao;

public class ErroDto {

    private String campo;
    private String erro;

    public ErroDto(String campo, String erro) {
        this.campo = campo;
        this.erro = erro;
    }

    public String getCampo() {
        return campo;
    }

    public String getErro() {
        return erro;
    }

}
