package br.edu.ifba.demo.frontend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifba.demo.frontend.dto.LivroDTO;
import br.edu.ifba.demo.frontend.service.LivroService;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/livro")
public class LivroController {
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

    // * Visualizar livro (get by ID) */
    @GetMapping("/view/{id}")
    public ModelAndView viewLivro(@PathVariable Long id) {
        LivroDTO liv = livroService.getByIdLivro(id);
        ModelAndView mv = new ModelAndView("/livro/form");
        mv.addObject("liv", liv);
        mv.addObject("isView", true);
        return mv;
    }
    
    //* Cadstrar livro */
    @GetMapping("/cadastro")
    public ModelAndView cadastroLivro() {
        ModelAndView mv = new ModelAndView("livro/form");
        mv.addObject("liv", new LivroDTO());
        mv.addObject("isView", false);
        return mv;
    }

    //* Salvar livro */
    @PostMapping("/")
    public ModelAndView salvarLivro(@RequestBody LivroDTO livro) {
        System.out.println(livro);
        livroService.salvarOuAtualizarLivro(livro);
        ModelAndView mv = new ModelAndView("redirect:/livro/");
        return mv;
    }

    //* Editar livro */
    @GetMapping("/edit/{id}")
    public ModelAndView editarLivro(@PathVariable Long id) {
        LivroDTO liv = livroService.getByIdLivro(id);
        ModelAndView mv = new ModelAndView("livro/form");
        mv.addObject("liv", liv);
        mv.addObject("isView", false);
        return mv;
    }

    //* Deletar livro */
    @GetMapping("/delete/{id}")
    public ModelAndView deleteLivro(@PathVariable Long id) {
        livroService.deleteLivro(id);
        ModelAndView mv = new ModelAndView("redirect:/livro/");
        return mv;
    }
}
