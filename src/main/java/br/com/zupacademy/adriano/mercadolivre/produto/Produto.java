package br.com.zupacademy.adriano.mercadolivre.produto;

import br.com.zupacademy.adriano.mercadolivre.categoria.Categoria;
import br.com.zupacademy.adriano.mercadolivre.opniao.Opniao;
import br.com.zupacademy.adriano.mercadolivre.pergunta.Pergunta;
import br.com.zupacademy.adriano.mercadolivre.usuario.Usuario;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Categoria categoria;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario dono;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    Set<@Valid CaracteristicaProduto> caracteristicas = new HashSet<>();

    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    Set<@Valid ImagemProduto> imagens = new HashSet<>();

    @OneToMany(mappedBy = "produto")
    Set<Opniao> opnioes = new HashSet<>();

    @OneToMany(mappedBy = "produto")
    List<Pergunta> perguntas = new ArrayList<>();

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

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public Usuario getDono() {
        return dono;
    }

    public void adicionarImagens(@Size(min = 1) Set<String> imagens) {
        Set<ImagemProduto> imagensDoProduto = imagens.stream().map(imagem -> {
            return new ImagemProduto(imagem, this);
        }).collect(Collectors.toSet());
        this.imagens.addAll(imagensDoProduto);
    }
    public <T> Set<T> mapeiaCaracteristicas(
            Function<CaracteristicaProduto, T> funcaoMapeadora) {
        return this.caracteristicas.stream().map(funcaoMapeadora)
                .collect(Collectors.toSet());
    }

    public <T> Set<T> mapeiaImagens(Function<ImagemProduto, T> funcaoMapeadora) {
        return this.imagens.stream().map(funcaoMapeadora)
                .collect(Collectors.toSet());
    }

    public <T extends Comparable<T>> SortedSet<T> mapeiaPerguntas(Function<Pergunta, T> funcaoMapeadora) {
        return this.perguntas.stream().map(funcaoMapeadora)
                .collect(Collectors.toCollection(TreeSet :: new));
    }

    public Opnioes getOpinioes() {
        return new Opnioes(this.opnioes);
    }

    public boolean temEstoque(@NotNull @Min(1) Integer quantidade) {
        return this.quantidadeDisponivel >= quantidade;
    }

    public void abateEstoque(@NotNull @Positive Integer quantidade) {
        Assert.isTrue(quantidade > 0, "A quantidade deve ser maior que zero para abater o estoque " + quantidade);
        if (this.temEstoque(quantidade)) {
            this.quantidadeDisponivel -= quantidade;
        }
    }
}
