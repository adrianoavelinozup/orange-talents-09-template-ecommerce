package br.com.zupacademy.adriano.mercadolivre.usuario;

import br.com.zupacademy.adriano.mercadolivre.seguranca.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class UsuarioControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final String URI = "/usuarios";

    @AfterEach
    void setup() {
        usuarioRepository.deleteAll();
    }

    @Test
    @DisplayName("Deve cadastrar um novo usuário")
    void test1() throws Exception {
        UsuarioRequest usuarioRequest = new UsuarioRequest("teste@email.com.br", "123456");

        String content = objectMapper.writeValueAsString(usuarioRequest);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @ParameterizedTest
    @CsvSource(value = {"1", "12345"})
    @DisplayName("Deve retornar erro quando senha inválida")
    void test2(String senha) throws Exception {
        UsuarioRequest usuarioRequest = new UsuarioRequest("teste@email.com.br", senha);
        String content = objectMapper.writeValueAsString(usuarioRequest);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();
    }

    @DisplayName("Deve retornar erro quando email inválido")
    @ParameterizedTest
    @CsvSource(value = {"email.email.com", "email", "''"})
    void test3(String email) throws Exception {

        UsuarioRequest usuarioRequest = new UsuarioRequest(email, "123456");
        String content = objectMapper.writeValueAsString(usuarioRequest);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();
    }

    @Test
    @DisplayName("Deve retornar erro quando email já está cadastrado")
    void test4() throws Exception {
        UsuarioRequest usuarioRequest = new UsuarioRequest("email@email.com", "123456");
        usuarioRepository.save(usuarioRequest.toModel());

        String content = objectMapper.writeValueAsString(usuarioRequest);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();
    }
}
