package br.com.zupacademy.adriano.mercadolivre.produto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProdutoDetalhadoResponse {
    private String nome;
    private BigDecimal preco;
    private String descricao;
    private Long notaMedia;
    private Long totalDeAvaliacoes;
    private List<CaracteristicaProdutoResponse> caracteristicas = new ArrayList<>();
    private List<String> imagens;
    private List<OpniaoResponse> opnioes = new ArrayList<>();
    private List<PerguntaResponse> perguntas = new ArrayList<>();

    public ProdutoDetalhadoResponse(Produto produto, Long notaMedia, Long totalDeAvaliacoes) {
        this.nome = produto.getNome();
        this.preco = produto.getValor();
        this.descricao = produto.getDescricao();
        this.notaMedia = notaMedia;
        this.totalDeAvaliacoes = totalDeAvaliacoes;
        this.caracteristicas.addAll(produto.getCaracteristicas().stream()
                .map(CaracteristicaProdutoResponse::new).collect(Collectors.toList()));
        this.imagens = produto.getImagens().stream()
                .map(ImagemProduto::getUrl).collect(Collectors.toList());
        this.perguntas = produto.getPerguntas().stream()
                .map(PerguntaResponse::new).collect(Collectors.toList());
        this.opnioes = produto.getOpnioes().stream()
                .map(OpniaoResponse::new).collect(Collectors.toList());
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public Long getNotaMedia() {
        return notaMedia;
    }

    public Long getTotalDeAvaliacoes() {
        return totalDeAvaliacoes;
    }

    public List<CaracteristicaProdutoResponse> getCaracteristicas() {
        return caracteristicas;
    }

    public List<String> getImagens() {
        return imagens;
    }

    public List<OpniaoResponse> getOpnioes() {
        return opnioes;
    }

    public List<PerguntaResponse> getPerguntas() {
        return perguntas;
    }
}
