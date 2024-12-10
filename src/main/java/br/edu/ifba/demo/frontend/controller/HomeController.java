package br.edu.ifba.demo.frontend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/* import br.edu.ifba.demo.frontend.service.UsuarioService; */
import br.edu.ifba.demo.frontend.dto.LivroDTO;
import br.edu.ifba.demo.frontend.service.LivroService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@Controller
public class HomeController {
    /* @Autowired
    private UsuarioService usuarioService; */

    @Autowired
    private LivroService livroService;

    // * Listar todos os livros */
    @GetMapping("/")
    public ModelAndView index() {
        List<LivroDTO> listaLiv = livroService.listAllLivros();
        ModelAndView mv = new ModelAndView();
        mv.addObject("listaLivros", listaLiv);
        mv.setViewName("index");
        return mv;
    }

    @GetMapping("/livro/getById/{id}")
    public ModelAndView getByIdLivro(@PathVariable Long id) {
        LivroDTO liv = livroService.getByIdLivro(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("livroPorId", liv);
        mv.setViewName("index");
        return mv;
    }

    @GetMapping("/livro/getByTitulo/{titulo}")
    public ModelAndView getByTitulo(@PathVariable String titulo) {
        LivroDTO liv = livroService.getByTitulo(titulo);
        ModelAndView mv = new ModelAndView();
        mv.addObject("livroPorTitulo", liv);
        mv.setViewName("index");
        return mv;
    }

    @GetMapping("/livro/getByIsbn/{isbn}")
    public ModelAndView getByIsbn(@PathVariable Integer isbn) {
        LivroDTO liv = livroService.getByIsbn(isbn);
        ModelAndView mv = new ModelAndView();
        mv.addObject("livroPorIsbn", liv);
        mv.setViewName("index");
        return mv;
    }

    @PostMapping("/livro")
    public ModelAndView addLivro(@RequestBody LivroDTO livro) {
        LivroDTO liv = livroService.addLivro(livro);
        ModelAndView mv = new ModelAndView();
        mv.addObject("livroCadastrado", liv);
        mv.setViewName("index");
        return mv;
    }
    

    @GetMapping("/livro/{id}")
    public ModelAndView deleteLivro(@PathVariable Long id) {
        boolean valor = livroService.deleteLivro(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("delete", valor);
        mv.setViewName("index");
        return mv;
    }
}
