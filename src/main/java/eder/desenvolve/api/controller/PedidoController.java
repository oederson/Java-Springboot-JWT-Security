package eder.desenvolve.api.controller;

import eder.desenvolve.api.dominio.pedido.DadosDetalhamentoPedido;
import eder.desenvolve.api.dominio.pedido.DadosPedido;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SecurityRequirement(name = "bearer-key")
@RequestMapping("pedido")
public class PedidoController {

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarPedido(@RequestBody @Valid DadosPedido dados){
        System.out.println(dados);
        return ResponseEntity.ok(new DadosDetalhamentoPedido (null,null, null, null));
    }
}
