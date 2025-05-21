package io.github.tessla2.controle_financeiro.controller;


import io.github.tessla2.controle_financeiro.entities.MovimentacaoFinanceira;
import io.github.tessla2.controle_financeiro.repository.MovimentacaoFinanceiraRepository;
import io.github.tessla2.controle_financeiro.service.MovimentacaoFinanceiraService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("api/movimentacoes")
@CrossOrigin(origins = "*")
public class MovimentacaoFinanceiraController {

    private final MovimentacaoFinanceiraService service;

    public MovimentacaoFinanceiraController(MovimentacaoFinanceiraService service) {
        this.service = service;
    }

    @GetMapping
    public List<MovimentacaoFinanceira> listaTodas(){
        return service.listarTodas();
    }

    @PostMapping
    public ResponseEntity<MovimentacaoFinanceira> criar(@RequestBody MovimentacaoFinanceira mov){
        return ResponseEntity.ok(service.salvar(mov));
    }

    @GetMapping("/saldo")
    public BigDecimal saldoMes(@RequestParam int ano, @RequestParam int mes){
        return service.calcularSaldoDoMes(ano, mes);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        service.deletar(id);
    }
}
