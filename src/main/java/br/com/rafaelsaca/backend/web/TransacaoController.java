package br.com.rafaelsaca.backend.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rafaelsaca.backend.entity.TransacaoReport;
import br.com.rafaelsaca.backend.service.TransacaoService;

@RestController
@RequestMapping("transacoes")
public class TransacaoController {

    private final TransacaoService service;

    public TransacaoController(TransacaoService service) {
        this.service = service;
    }

    @GetMapping
    List<TransacaoReport> listAll() {
        return service.listTotaisTransacaoPorNomeDaLoja();
    }
}
