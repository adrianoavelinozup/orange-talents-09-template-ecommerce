package br.com.zupacademy.adriano.mercadolivre.usuario;

import javax.validation.constraints.*;

public class UsuarioRequest {

    @NotBlank
    @Email
    private String login;

    @NotBlank
    @Size(min = 6)
    private String senha;

    public UsuarioRequest(
            @NotBlank @Email String login,
            @NotBlank @Size(min = 6) String senha) {
        this.login = login;
        this.senha = senha;
    }

    public Usuario toModel() {
        return new Usuario(this.login, this.senha);
    }
}
