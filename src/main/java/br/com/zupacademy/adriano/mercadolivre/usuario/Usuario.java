package br.com.zupacademy.adriano.mercadolivre.usuario;

import br.com.zupacademy.adriano.mercadolivre.opniao.Opniao;
import br.com.zupacademy.adriano.mercadolivre.pergunta.Pergunta;
import br.com.zupacademy.adriano.mercadolivre.produto.Produto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "usuarios", uniqueConstraints = {
    @UniqueConstraint(name = "uc_usuario_email", columnNames = "email")
})
public class Usuario {
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) long id;

    @Column(nullable = false)
    private @NotBlank @Email String email;

    @Column(nullable = false)
    private @NotBlank @Size(min = 6) String senha;

    @Column(nullable = false)
    private @PastOrPresent LocalDateTime dataCriacao;

    @Deprecated
    public Usuario() {
    }

    public Usuario(@NotBlank @Email String email,
                   @NotBlank @Size(min = 6) SenhaLimpa senhaLimpa) {
        Assert.isTrue(StringUtils.hasLength(email), "O email não pode estar em branco");
        Assert.notNull(senhaLimpa, "OP objeto do tipo senha limpa  não pode ser nulo");

        this.email = email;
        this.senha = senhaLimpa.hash();
        this.dataCriacao = LocalDateTime.now();
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return id == usuario.id && email.equals(usuario.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }
}
