package br.com.zupacademy.adriano.mercadolivre.usuario;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "usuarios")
@Validated
public class Usuario {
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) long id;

    @Column(nullable = false)
    private @NotBlank @Email String login;

    @Column(nullable = false)
    private @NotBlank @Size(min = 6) String senha;

    @Column(nullable = false)
    private @PastOrPresent LocalDateTime dataCriacao;

    public Usuario(@NotBlank @Email String login,
                   @NotBlank @Size(min = 6) String senha) {
        this.login = login;
        this.senha = this.codificarSenha(senha);
        this.dataCriacao = LocalDateTime.now();
    }

    private String codificarSenha(String pasword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(pasword);
    }
}
