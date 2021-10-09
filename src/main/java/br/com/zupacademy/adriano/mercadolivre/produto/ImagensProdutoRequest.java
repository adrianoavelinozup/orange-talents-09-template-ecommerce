package br.com.zupacademy.adriano.mercadolivre.produto;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class ImagensProdutoRequest {
    @Size(min = 1)
    @NotNull
    private List<MultipartFile> imagens;

    public ImagensProdutoRequest(List<MultipartFile> imagens) {
        this.imagens = imagens;
    }

    public List<MultipartFile> getImagens() {
        return imagens;
    }
}
