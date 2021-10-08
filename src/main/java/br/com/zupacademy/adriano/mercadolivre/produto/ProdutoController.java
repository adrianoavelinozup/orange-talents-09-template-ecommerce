package br.com.zupacademy.adriano.mercadolivre.produto;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @PostMapping
    public String cadastrar(@RequestBody @Valid ProdutoRequest produtoRequest) {
        return produtoRequest.toString();
    }
}
