package br.com.zupacademy.adriano.mercadolivre.produto;

import br.com.zupacademy.adriano.mercadolivre.categoria.Categoria;
import br.com.zupacademy.adriano.mercadolivre.usuario.Usuario;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "produtos")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @NotNull @Positive
    @Column(nullable = false)
    private BigDecimal valor;

    @NotNull @Min(0)
    @Column(nullable = false)
    private Integer quantidadeDisponivel;

    @NotBlank @Length(max = 1000)
    @Column(nullable = false)
    private String descricao;

    @NotNull
    private LocalDateTime dataCriacao = LocalDateTime.now();

    @ManyToOne
    @NotNull
    private Categoria categoria;

    @NotNull
    @ManyToOne
    private Usuario dono;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    Set<@Valid CaracteristicaProduto> caracteristicas = new HashSet<>();

    @Deprecated
    public Produto() {
    }

    public Produto(
            @NotBlank String nome,
            @NotNull @Positive BigDecimal valor,
            @NotNull @Min(0) Integer quantidadeDisponivel,
            @NotBlank @Length(max = 1000) String descricao,
            @NotNull Categoria categoria,
            @NotNull @Size(min = 3) Collection<@Valid CaracteristicaProdutoRequest> caracteristicas,
            Usuario dono) {
        this.nome = nome;
        this.valor = valor;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.descricao = descricao;
        this.categoria = categoria;
        Set<CaracteristicaProduto> conjuntoCaracteristicas = caracteristicas.stream()
                .map(cr -> cr.toModel(this))
                .collect(Collectors.toSet());

        this.caracteristicas.addAll(conjuntoCaracteristicas);
        this.dono = dono;
        Assert.isTrue(this.caracteristicas.size() >= 3, "Todo produto precisa ter no mínimo 3 ou mais características");
    }

}
