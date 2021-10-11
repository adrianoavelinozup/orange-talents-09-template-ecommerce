package br.com.zupacademy.adriano.mercadolivre.produto;

import java.math.BigDecimal;
import java.util.*;

public class ProdutoDetalhadoResponse {
    private String nome;
    private BigDecimal preco;
    private String descricao;
    private double notaMedia;
    private int totalDeAvaliacoes;
    private Set<CaracteristicaProdutoResponse> caracteristicas;
    private Set<String> imagens;
    private Set<Map<String,String>> opinioes;
    private SortedSet<String> perguntas;

    public ProdutoDetalhadoResponse(Produto produto) {
        this.nome = produto.getNome();
        this.preco = produto.getValor();
        this.descricao = produto.getDescricao();
        this.notaMedia = notaMedia;
        this.totalDeAvaliacoes = totalDeAvaliacoes;
        this.caracteristicas = produto
                .mapeiaCaracteristicas(CaracteristicaProdutoResponse::new);
        this.imagens = produto.mapeiaImagens(imagem -> imagem.getUrl());
        this.perguntas = produto.mapeiaPerguntas(pergunta -> pergunta.getTitulo());

        Opnioes opnioes = produto.getOpinioes();
        this.opinioes = opnioes.mapeiaOpinioes(opiniao -> {
            return Map.of("titulo",opiniao.getTitulo(),"descricao",opiniao.getDescricao());
        });


        this.notaMedia = opnioes.media();
        this.totalDeAvaliacoes = opnioes.total();
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

    public double getNotaMedia() {
        return notaMedia;
    }

    public int getTotalDeAvaliacoes() {
        return totalDeAvaliacoes;
    }

    public Set<CaracteristicaProdutoResponse> getCaracteristicas() {
        return caracteristicas;
    }

    public Set<String> getImagens() {
        return imagens;
    }

    public Set<Map<String, String>> getOpnioes() {
        return opinioes;
    }

    public SortedSet<String> getPerguntas() {
        return perguntas;
    }
}
