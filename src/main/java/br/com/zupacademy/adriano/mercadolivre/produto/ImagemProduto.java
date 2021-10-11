package br.com.zupacademy.adriano.mercadolivre.produto;

import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "imagens_produto")
public class ImagemProduto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @URL
    @Column(nullable = false)
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Produto produto;

    @Deprecated
    public ImagemProduto() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImagemProduto that = (ImagemProduto) o;
        return url.equals(that.url) && produto.equals(that.produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, produto);
    }

    public ImagemProduto(String url, Produto produto) {
        this.url = url;
        this.produto = produto;
    }

    public String getUrl() {
        return url;
    }
}
