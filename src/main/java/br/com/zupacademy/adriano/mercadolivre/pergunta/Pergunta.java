package br.com.zupacademy.adriano.mercadolivre.pergunta;

import br.com.zupacademy.adriano.mercadolivre.produto.Produto;
import br.com.zupacademy.adriano.mercadolivre.usuario.Usuario;

import javax.persistence.*;
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
    @ManyToOne
    private Usuario usuario;

    @NotNull
    @ManyToOne
    private Produto produto;

    @NotNull
    private LocalDateTime dataCriacao = LocalDateTime.now();

    @Deprecated
    public Pergunta() {
    }

    public Pergunta(@NotBlank String titulo, @NotNull Usuario usuario, @NotNull Produto produto) {
        this.titulo = titulo;
        this.usuario = usuario;
        this.produto = produto;
    }
}
