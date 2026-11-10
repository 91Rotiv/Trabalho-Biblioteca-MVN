
package com.biblioteca.biblioteca.controller;

import com.biblioteca.biblioteca.model.Livro;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequestMapping("/livros")
public class LivroController {

    @GetMapping
    public String inicio(Model model) {

        Livro livro = new Livro();

        livro.setTitulo("Dom Casmurro");
        livro.setAutor("Machado de Assis");
        livro.setIsbn("978-85-00-00000-0");

        model.addAttribute("livro", livro);

        return "livros/inicio";
    }

    @GetMapping("/novo")
    public String novoLivro(Model model) {

        model.addAttribute("livro", new Livro());

        return "livros/form";
    }

    @PostMapping
    public String cadastrarLivro(
            @ModelAttribute("livro") Livro livro,
            Model model) {

        model.addAttribute("livro", livro);

        return "livros/confirmacao";
    }
}
