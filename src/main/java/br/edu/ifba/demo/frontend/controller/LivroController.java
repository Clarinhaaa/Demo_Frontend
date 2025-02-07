package br.edu.ifba.demo.frontend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifba.demo.frontend.dto.LivroDTO;
import br.edu.ifba.demo.frontend.service.GeneroService;
import br.edu.ifba.demo.frontend.service.LivroService;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/livro")
public class LivroController {
    @Autowired
    private LivroService livroService;
    @Autowired
    private GeneroService generoService;

    // * Listar todos */
    @GetMapping("/")
    public ModelAndView listarLivros() {
        List<LivroDTO> listaLiv = livroService.listAllLivros();
        ModelAndView mv = new ModelAndView();
        mv.addObject("listaLiv", listaLiv);
        mv.setViewName("index");
        return mv;
    }

    // * Visualizar (get by ID) */
    @GetMapping("/view/{id}")
    public ModelAndView viewLivro(@PathVariable Long id) {
        LivroDTO liv = livroService.getByIdLivro(id);
        ModelAndView mv = new ModelAndView("livro/form");
        mv.addObject("liv", liv);
        mv.addObject("isView", true);
        mv.addObject("listaGen", generoService.listAllGeneros());
        return mv;
    }
    
    //* Tela de cadstrar */
    @GetMapping("/cadastro")
    public ModelAndView cadastroLivro() {
        ModelAndView mv = new ModelAndView("livro/form");
        mv.addObject("liv", new LivroDTO());
        mv.addObject("isView", false);
        mv.addObject("listaGen", generoService.listAllGeneros());
        return mv;
    }

    //* Salvar */
    @PostMapping("/")
    public ModelAndView salvarLivro(@ModelAttribute("liv") LivroDTO liv) {
        livroService.salvarOuAtualizarLivro(liv);
        ModelAndView mv = new ModelAndView("redirect:/livro/");
        return mv;
    }

    //* Tela de editar */
    @GetMapping("/edit/{id}")
    public ModelAndView editarLivro(@PathVariable Long id) {
        LivroDTO liv = livroService.getByIdLivro(id);
        ModelAndView mv = new ModelAndView("livro/form");
        mv.addObject("liv", liv);
        mv.addObject("isView", false);
        mv.addObject("listaGen", generoService.listAllGeneros());
        return mv;
    }

    //* Deletar */
    @GetMapping("/delete/{id}")
    public ModelAndView deleteLivro(@PathVariable Long id) {
        livroService.deleteLivro(id);
        ModelAndView mv = new ModelAndView("redirect:/livro/");
        return mv;
    }
}
