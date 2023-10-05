package eder.desenvolve.api.controller;

import eder.desenvolve.api.dominio.produto.*;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@SecurityRequirement(name = "bearer-key")
@RequestMapping("/produto")
public class ProdutoController {
    @Autowired
    private ProdutoRepository produtoRepository;
    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroProduto dados ){
        produtoRepository.save(new Produto(dados));
    }
    @GetMapping
    public ResponseEntity<Page<DadosProdutos>> listar(Pageable paginacao){
        var page = produtoRepository.findAll(paginacao).map(DadosProdutos::new);
        return ResponseEntity.ok(page);
    }
    @GetMapping("/publico")
    public ResponseEntity<Page<DadosProdutos>> listarPublico(Pageable paginacao){
        var page = produtoRepository.findAll(paginacao).map(DadosProdutos::new);
        return ResponseEntity.ok(page);
    }
    @GetMapping("id/{id}")
    @Transactional
    public ResponseEntity porId(@PathVariable String id){
        Optional<Produto> produto = produtoRepository.findById(id);
        Produto produto1 = produto.get();
        return ResponseEntity.ok(new DadosProdutos(produto1));
    }
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<Page<DadosProdutosFiltradosPorCategoria>> listarPorCategoria(@PathVariable String categoria, Pageable paginacao) {
        Page<Produto> produtosPage = produtoRepository.findByCategoria(categoria, paginacao);
        List<DadosProdutosFiltradosPorCategoria> page = produtosPage.stream()
                .map(DadosProdutosFiltradosPorCategoria::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(new PageImpl<>(page, paginacao, produtosPage.getTotalElements()));
    }
    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoProduto dados ){
        var produtoOptional = produtoRepository.findById(dados.id());
        if (produtoOptional.isPresent()) {
            var produto = produtoOptional.get();
            produto.atualizarInfo(dados);
            produtoRepository.save(produto); }
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable String id) {
        produtoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}