package br.com.zupacademy.adriano.mercadolivre.produto;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Primary
public class UploadDev implements Uploader {

    @Value("${mercadolivre.upload.url-base}")
    private String urlBase;

    @Override
    public Set<String> enviar(List<MultipartFile> imagens) {
        return imagens.stream().map( imagem -> {
            return urlBase + imagem.getOriginalFilename();
        }).collect(Collectors.toSet());
    }
}
