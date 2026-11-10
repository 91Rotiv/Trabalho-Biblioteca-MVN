package com.biblioteca.biblioteca.controller;

import com.biblioteca.biblioteca.model.Categoria;
import com.biblioteca.biblioteca.repository.CategoriaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaRepository repository;

    public CategoriaController(CategoriaRepository repository) {
        this.repository = repository;
    }

    // Listar todas as categorias cadastradas
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("categorias", repository.findAll());
        return "categorias/lista";
    }

    // Abrir o formulario de cadastro
    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "categorias/form";
    }

    // Receber e salvar a categoria
    @PostMapping
    public String cadastrar(@ModelAttribute Categoria categoria) {
        repository.save(categoria);
        return "redirect:/categorias";
    }

    // Excluir pelo botao da listagem HTML
    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
        return "redirect:/categorias";
    }

    // Endpoint HTTP DELETE (por exemplo, para testar no Postman)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPorId(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
