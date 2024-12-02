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
        Mono<List<LivroDTO>> livroList = this.webClient.method(HttpMethod.GET).uri("livro/listall").retrieve()
                .bodyToFlux(LivroDTO.class).collectList();

        List<LivroDTO> livroListRetornar = livroList.block();
        return livroListRetornar;
    }

    // * Pegar o livro pelo ID */
    public LivroDTO getByIdLivro(Long id_livro) {
        Mono<LivroDTO> livro = this.webClient.method(HttpMethod.GET).uri("livro/getById/{id}", id_livro).retrieve()
                .bodyToMono(LivroDTO.class);

        LivroDTO livroRetornar = livro.block();
        return livroRetornar;
    }

    // * Pegar o livro pelo ISBN */
    public LivroDTO getByIsbn(Integer isbn) {
        Mono<LivroDTO> livro = this.webClient.method(HttpMethod.GET).uri("livro/getByIsbn/{isbn}", isbn).retrieve()
                .bodyToMono(LivroDTO.class);

        LivroDTO livroRetornar = livro.block();
        return livroRetornar;
    }

    // * Pegar o livro pelo título */
    public LivroDTO getByTitulo(String titulo) {
        Mono<LivroDTO> livro = this.webClient.method(HttpMethod.GET).uri("livro/getByTitulo/{titulo}", titulo)
                .retrieve()
                .bodyToMono(LivroDTO.class);

        LivroDTO livroRetornar = livro.block();
        return livroRetornar;
    }

    // * Adicionar livro */
    public LivroDTO addLivro(LivroDTO livroCadastrar) {
        return this.webClient.method(HttpMethod.POST).uri("livro").bodyValue(livroCadastrar).retrieve().bodyToMono(LivroDTO.class)
                .block();
    }

    // * Deletar livro */
    public boolean deleteLivro(Long id_livro) {
        Mono<LivroDTO> livroList = this.webClient.method((HttpMethod.DELETE)).uri("livro/{idLivro}", id_livro)
                .retrieve().bodyToMono(LivroDTO.class);
        
        LivroDTO livro = livroList.block();
        if (livro != null)
            return true;
        return false;
    }
}
