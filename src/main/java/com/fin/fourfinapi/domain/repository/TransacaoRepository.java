package com.fin.fourfinapi.domain.repository;

import com.fin.fourfinapi.domain.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

    List<Transacao> findAllByAnotacaoContaining(String anotacao);

    List<Transacao> findAllByValorBetween(BigDecimal valorInicial, BigDecimal valorFinal);

    @Query("SELECT COALESCE(SUM(t.valor), 0) FROM Transacao t WHERE t.categoria.id = :categoriaId")
    BigDecimal somarValorPorCategoria(@Param("categoriaId") Long categoriaId);

    @Query("SELECT COALESCE(SUM(t.valor), 0) FROM Transacao t WHERE t.conta.id = :contaId")
    BigDecimal somarValorPorConta(@Param("contaId") Long contaId);
    
}
