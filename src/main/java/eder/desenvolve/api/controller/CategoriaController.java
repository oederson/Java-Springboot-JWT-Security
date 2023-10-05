package eder.desenvolve.api.controller;


import eder.desenvolve.api.dominio.categorias.Categoria;
import eder.desenvolve.api.dominio.categorias.CategoriaRepository;
import eder.desenvolve.api.dominio.categorias.DadosAtualizacaoCategoria;
import eder.desenvolve.api.dominio.categorias.DadosCategoria;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@SecurityRequirement(name = "bearer-key")
@RequestMapping("categorias")
public class CategoriaController {
    @Autowired
    private CategoriaRepository categoriaRepository;
    @PostMapping
    @Transactional
    public ResponseEntity cadastrarCategoria(@RequestBody @Valid DadosCategoria categoria){
        categoriaRepository.save(new Categoria(categoria));
        return ResponseEntity.ok(new Categoria(categoria));
    }
    @GetMapping
    public ResponseEntity<Page<DadosCategoria>> listarPublico(Pageable paginacao){
        var page = categoriaRepository.findAll(paginacao).map(DadosCategoria::new);
        return ResponseEntity.ok(page);
    }
    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity porId(@PathVariable String id){
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        Categoria categorias = categoria.get();
        return ResponseEntity.ok(new DadosCategoria(categorias));
    }
    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoCategoria categoria ){
        var categoriaOptional = categoriaRepository.findById(categoria.id());
        if (categoriaOptional.isPresent()) {
            var categoria1 = categoriaOptional.get();
            categoria1.atualizarInfo(categoria);
            categoriaRepository.save(categoria1); }
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable String id) {
        categoriaRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
