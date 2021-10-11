package br.com.zupacademy.adriano.mercadolivre.pergunta;

import br.com.zupacademy.adriano.mercadolivre.produto.Produto;
import br.com.zupacademy.adriano.mercadolivre.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "perguntas")
public class Pergunta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String titulo;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @Valid
    private Usuario usuario;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @Valid
    private Produto produto;

    @NotNull
    private LocalDateTime dataCriacao;

    @Deprecated
    public Pergunta() {
    }

    public Pergunta(@NotBlank String titulo, @NotNull @Valid Usuario usuario, @NotNull @Valid Produto produto) {
        this.titulo = titulo;
        this.usuario = usuario;
        this.produto = produto;
        this.dataCriacao = LocalDateTime.now();
    }

    public String getTitulo() {
        return titulo;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public Produto getProduto() {
        return this.produto;
    }

    public String getEmailDoDonoDoProduto() {
        return this.produto.getDono().getEmail();
    }
}
