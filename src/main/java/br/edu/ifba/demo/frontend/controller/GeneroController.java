package br.edu.ifba.demo.frontend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.ifba.demo.frontend.dto.GeneroDTO;
import br.edu.ifba.demo.frontend.service.GeneroService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;





@Controller
@RequestMapping("/genero")
public class GeneroController {
    @Autowired
    GeneroService generoService;

    //* Listar todos */
    @GetMapping("/")
    public ModelAndView listarGeneros() {
        List<GeneroDTO> listaGen = generoService.listAllGeneros();
        ModelAndView mv = new ModelAndView("genero/list");
        mv.addObject("listaGen", listaGen);
        return mv;
    }
    
    //* Visualizar (get by ID) */
    @GetMapping("/view/{id}")
    public ModelAndView viewGenero(@PathVariable Long id) {
        GeneroDTO gen = generoService.getByIdGenero(id);
        ModelAndView mv = new ModelAndView("genero/form");
        mv.addObject("gen", gen);
        mv.addObject("isView", true);
        return mv;
    }

    //* Tela de cadastrar */
    @GetMapping("/cadastro")
    public ModelAndView cadastrarGenero() {
        ModelAndView mv = new ModelAndView("genero/form");
        mv.addObject("gen", new GeneroDTO());
        mv.addObject("isView", false);
        return mv;
    }

    //* Salvar/Atualizar */
    @PostMapping("/")
    public ModelAndView salvarGenero(@ModelAttribute("gen") GeneroDTO gen) {
        generoService.salvarOuAtualizarGenero(gen);
        ModelAndView mv = new ModelAndView("redirect:/genero/");
        return mv;
    }
    
    //* Tela de editar */
    @GetMapping("/edit/{id}")
    public ModelAndView editarGenero(@PathVariable Long id) {
        GeneroDTO gen = generoService.getByIdGenero(id);
        ModelAndView mv = new ModelAndView("genero/form");
        mv.addObject("gen", gen);
        mv.addObject("isView", false);
        return mv;
    }
    
    //* Deletar */
    @GetMapping("/delete/{id}")
    public ModelAndView deleteGenero(@PathVariable Long id) {
        generoService.deleteGenero(id);
        ModelAndView mv = new ModelAndView("redirect:/genero");
        return mv;
    }
    
}
