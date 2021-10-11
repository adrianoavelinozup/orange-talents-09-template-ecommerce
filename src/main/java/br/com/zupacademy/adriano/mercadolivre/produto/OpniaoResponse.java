package br.com.zupacademy.adriano.mercadolivre.produto;

import br.com.zupacademy.adriano.mercadolivre.opniao.Opniao;

public class OpniaoResponse {
    private Integer nota;
    private String titulo;
    private String descricao;
    private String email;

    public OpniaoResponse(Opniao opniao) {
        this.nota = opniao.getNota();
        this.titulo = opniao.getTitulo();
        this.descricao = opniao.getDescricao();
        this.email = opniao.getUsuario().getEmail();
    }

    public Integer getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getEmail() {
        return email;
    }
}
