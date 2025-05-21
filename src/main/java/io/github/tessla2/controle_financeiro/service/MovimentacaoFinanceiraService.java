package io.github.tessla2.controle_financeiro.service;


import io.github.tessla2.controle_financeiro.TipoMovimentacao;
import io.github.tessla2.controle_financeiro.entities.MovimentacaoFinanceira;
import io.github.tessla2.controle_financeiro.repository.MovimentacaoFinanceiraRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Service
public class MovimentacaoFinanceiraService {
    private final MovimentacaoFinanceiraRepository repository;


    public MovimentacaoFinanceiraService(MovimentacaoFinanceiraRepository movimentacaoFinanceiraRepository, MovimentacaoFinanceiraRepository repository) {
        this.repository = repository;
    }

    public List<MovimentacaoFinanceira> listarTodas() {
        return repository.findAll();
    }

    public MovimentacaoFinanceira salvar(MovimentacaoFinanceira mov) {
        return repository.save(mov);
    }

    public List<MovimentacaoFinanceira> buscarPorMes(int ano, int mes){
        YearMonth yearMonth = YearMonth.of(ano, mes);
        LocalDate inicio = yearMonth.atDay(1);
        LocalDate fim = yearMonth.atEndOfMonth();
        return repository.findByDataBetween(inicio, fim);
    }

    public BigDecimal calcularSaldoDoMes(int ano, int mes) {
        List<MovimentacaoFinanceira> lista = buscarPorMes(ano, mes);
        BigDecimal saldo = BigDecimal.ZERO;

        for (MovimentacaoFinanceira mov : lista) {
            if(mov.getTipo() == TipoMovimentacao.RECEITA){
                saldo = saldo.add(mov.getValor());
            }

        }
        return saldo;
    }

    public void deletar(Long id){
        repository.deleteById(id);
    }


}
