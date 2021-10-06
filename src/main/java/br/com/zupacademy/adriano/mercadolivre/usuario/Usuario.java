package br.com.zupacademy.adriano.mercadolivre.usuario;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios")
@Validated
public class Usuario {
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) long id;

    @Column(nullable = false)
    private @NotBlank @Email String email;

    @Column(nullable = false)
    private @NotBlank @Size(min = 6) String senha;

    @Column(nullable = false)
    private @PastOrPresent LocalDateTime dataCriacao;

    public Usuario(@NotBlank @Email String email,
                   @NotBlank @Size(min = 6) SenhaLimpa senhaLimpa) {
        Assert.isTrue(StringUtils.hasLength(email), "O email não pode estar em branco");
        Assert.notNull(senhaLimpa, "OP objeto do tipo senha limpa  não pode ser nulo");

        this.email = email;
        this.senha = senhaLimpa.hash();
        this.dataCriacao = LocalDateTime.now();
    }
}
