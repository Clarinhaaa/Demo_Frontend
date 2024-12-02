package br.edu.ifba.demo.frontend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.edu.ifba.demo.frontend.service.UsuarioService;
import br.edu.ifba.demo.frontend.dto.LivroDTO;
import br.edu.ifba.demo.frontend.service.LivroService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private LivroService livroService;

    // * Listar todos os livros */
    @GetMapping("/")
    public ModelAndView index() {
        List<LivroDTO> li = livroService.listAllLivros();
        ModelAndView mv = new ModelAndView();
        mv.addObject("listaLivros", li);
        mv.setViewName("index");
        return mv;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable int id) {
        boolean valor = usuarioService.delete(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("delete", valor);
        mv.setViewName("index");
        return mv;
    }
}
