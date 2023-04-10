package com.fin.fourfinapi.domain.repository;

import com.fin.fourfinapi.domain.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

}
