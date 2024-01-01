package br.com.rafaelsaca.backend.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.rafaelsaca.backend.entity.Transacao;

@Repository
public interface TransacaoRepository extends CrudRepository<Transacao, Long> {

    // select * from transacao order by nome_loja asc and id desc
    List<Transacao> findAllByOrderByNomeDaLojaAscIdDesc();
}