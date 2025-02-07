package br.edu.ifba.demo.frontend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import br.edu.ifba.demo.frontend.dto.LivroDTO;
import reactor.core.publisher.Mono;

@Service
public class LivroService {
    @Autowired
    private WebClient webClient;

    // * Listar todos os livros */
    public List<LivroDTO> listAllLivros() {
        Mono<List<LivroDTO>> listaLiv = this.webClient.method(HttpMethod.GET).uri("livro/listall").retrieve()
                .bodyToFlux(LivroDTO.class).collectList();

        List<LivroDTO> listaLivRetornar = listaLiv.block();
        return listaLivRetornar;
    }

    // * Pegar o livro pelo ID */
    public LivroDTO getByIdLivro(Long id_livro) {
        Mono<LivroDTO> livro = this.webClient.method(HttpMethod.GET).uri("livro/getById/{id}", id_livro).retrieve()
                .bodyToMono(LivroDTO.class);

        LivroDTO livroRetornar = livro.block();
        return livroRetornar;
    }

    // * Adicionar/Editar livro */
    public boolean salvarOuAtualizarLivro(LivroDTO livObj) {
        Mono<LivroDTO> object = this.webClient.method(HttpMethod.POST).uri("livro", livObj).bodyValue(livObj).retrieve()
                .bodyToMono(LivroDTO.class);
        
        LivroDTO liv = object.block();
        if (liv != null)
            return true;
        return false;
    }

    // * Deletar livro */
    public boolean deleteLivro(Long id_livro) {
        Mono<LivroDTO> livObj = this.webClient.method((HttpMethod.DELETE)).uri("livro/{id}", id_livro)
                .retrieve().bodyToMono(LivroDTO.class);

        LivroDTO liv = livObj.block();
        if (liv != null)
            return true;
        return false;
    }
}
