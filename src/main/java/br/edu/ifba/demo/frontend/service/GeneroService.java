package br.edu.ifba.demo.frontend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import br.edu.ifba.demo.frontend.dto.GeneroDTO;
import reactor.core.publisher.Mono;

@Service
public class GeneroService {
    @Autowired
    private WebClient webClient;

    // * Listar todos os gêneros */
    public List<GeneroDTO> listAllGeneros() {
        Mono<List<GeneroDTO>> listaGen = this.webClient.method(HttpMethod.GET).uri("genero/listall").retrieve()
                .bodyToFlux(GeneroDTO.class).collectList();

        List<GeneroDTO> listaGenRetornar = listaGen.block();
        return listaGenRetornar;
    }

    // * Pegar o gênero pelo ID */
    public GeneroDTO getByIdGenero(Long id_genero) {
        Mono<GeneroDTO> gen = this.webClient.method(HttpMethod.GET).uri("genero/getById/{id}", id_genero).retrieve()
                .bodyToMono(GeneroDTO.class);

        GeneroDTO genRetornar = gen.block();
        return genRetornar;
    }

    // * Adicionar/Editar */
    public boolean salvarOuAtualizarGenero(GeneroDTO genObj) {
        Mono<GeneroDTO> object = this.webClient.method(HttpMethod.POST).uri("genero", genObj).bodyValue(genObj)
                .retrieve().bodyToMono(GeneroDTO.class);

        GeneroDTO gen = object.block();
        if (gen != null)
            return true;
        return false;
    }

    // * Deletar */
    public boolean deleteGenero(Long id_genero) {
        Mono<GeneroDTO> genObj = this.webClient.method(HttpMethod.DELETE).uri("genero/{id}", id_genero).retrieve()
                .bodyToMono(GeneroDTO.class);
        
        GeneroDTO gen = genObj.block();
        if (gen != null)
            return true;
        return false;
    }
}
