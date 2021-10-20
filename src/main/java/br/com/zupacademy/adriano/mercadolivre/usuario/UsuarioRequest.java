package br.com.zupacademy.adriano.mercadolivre.usuario;

import br.com.zupacademy.adriano.mercadolivre.validacoes.ValorUnico;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UsuarioRequest {

    @NotBlank
    @Email
    @ValorUnico(classeDaEntidade = Usuario.class, nomeDoCampo = "email")
    private String email;

    @NotBlank
    @Size(min = 6)
    private String senha;

    public UsuarioRequest(
            @NotBlank @Email String email,
            @NotBlank @Size(min = 6) String senha) {
        this.email = email;
        this.senha = senha;
    }

    public Usuario toModel() {
        return new Usuario(this.email, new SenhaLimpa(this.senha)) ;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }
}
