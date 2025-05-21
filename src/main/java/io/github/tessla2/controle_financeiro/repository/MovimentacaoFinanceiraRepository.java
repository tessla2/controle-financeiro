package io.github.tessla2.controle_financeiro.repository;

import io.github.tessla2.controle_financeiro.entities.MovimentacaoFinanceira;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MovimentacaoFinanceiraRepository extends JpaRepository<MovimentacaoFinanceira, Long> {
    List<MovimentacaoFinanceira> findByDataBetween(LocalDate inicio, LocalDate fim);
}
