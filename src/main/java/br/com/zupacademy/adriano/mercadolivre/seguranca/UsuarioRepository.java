package br.com.zupacademy.adriano.mercadolivre.seguranca;


import java.util.Optional;

import br.com.zupacademy.adriano.mercadolivre.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

}
