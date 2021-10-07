package br.com.zupacademy.adriano.mercadolivre.seguranca;

import java.util.Optional;

import br.com.zupacademy.adriano.mercadolivre.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        Optional<Usuario> usuario = repository.findByEmail(email);
//
//        if (usuario.isPresent()) {
//            return new UsuarioLogado(usuario.get());
//        }
//
//        throw new UsernameNotFoundException("Dados inválidos!");
        Usuario usuario = repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Dados inválidos!"));

        return new UsuarioLogado(usuario);
    }

}