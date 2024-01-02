package br.com.rafaelsaca.backend.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.rafaelsaca.backend.entity.TransacaoReport;
import br.com.rafaelsaca.backend.repository.TransacaoRepository;

@Service
public class TransacaoService {
    private final TransacaoRepository repository;

    public TransacaoService(TransacaoRepository repository) {
        this.repository = repository;
    }

    public List<TransacaoReport> listTotaisTransacaoPorNomeDaLoja() {
        var transacoes = repository.findAllByOrderByNomeDaLojaAscIdDesc();
        var reportMap = new LinkedHashMap<String, TransacaoReport>();// linkedHashMap pois preserva a ordem.

        transacoes.forEach(transacao -> {
            var nomeDaLoja = transacao.nomeDaLoja();
            var valor = transacao.valor();

            reportMap.compute(nomeDaLoja, (key, existingReport) -> {
                var report = (existingReport != null) ? existingReport
                        : new TransacaoReport(key, BigDecimal.ZERO, new ArrayList<>());

                return report.addTotal(valor).addTransacao(transacao);
            });
        });

        return new ArrayList<>(reportMap.values());

    }

}
