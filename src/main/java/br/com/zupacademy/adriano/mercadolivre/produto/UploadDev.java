package br.com.zupacademy.adriano.mercadolivre.produto;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Primary
public class UploadDev implements Uploader {

    @Override
    public Set<String> enviar(List<MultipartFile> imagens) {
        return imagens.stream().map( imagem -> {
            return new String("http://meusite.com.br/" + imagem.getOriginalFilename());
        }).collect(Collectors.toSet());
    }
}
