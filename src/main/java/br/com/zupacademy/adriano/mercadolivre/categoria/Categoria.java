package br.com.zupacademy.adriano.mercadolivre.categoria;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "categorias", uniqueConstraints = {
    @UniqueConstraint(name = "uc_categoria_nome", columnNames = "nome")
})
public class Categoria {
    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) long id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @ManyToOne
    private Categoria categoria;

    @Deprecated
    public Categoria() {
    }

    public Categoria(@NotBlank String nome) {
        Assert.isTrue(StringUtils.hasLength(nome), "O nome não pode estar em branco");
        this.nome = nome;
    }

    public void setCategoria(@NotNull Categoria categoria) {
        this.categoria = categoria;
    }
}
