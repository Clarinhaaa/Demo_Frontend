package br.edu.ifba.demo.frontend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HomeController {
    @Autowired
    private LivroController livroController;

    @GetMapping("/")
    public ModelAndView listarLivrosIndex() {
        ModelAndView mv = livroController.listarLivros();
        return mv;
    }
}