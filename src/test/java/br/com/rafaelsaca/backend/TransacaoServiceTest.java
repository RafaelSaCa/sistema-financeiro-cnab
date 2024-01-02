package br.com.rafaelsaca.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.rafaelsaca.backend.entity.Transacao;
import br.com.rafaelsaca.backend.repository.TransacaoRepository;
import br.com.rafaelsaca.backend.service.TransacaoService;

@ExtendWith(MockitoExtension.class)
public class TransacaoServiceTest {
   @InjectMocks
   private TransacaoService service;

   @Mock
   private TransacaoRepository repository;

   @Test
   public void testlistTotaisTransacoesByNomeDaLoja() {

      // AAA - 1ºA -Arrange
      final String lojaA = "Loja A", lojaB = "Loja B";

      var transacao1 = new Transacao(
            1L, 1, new Date(System.currentTimeMillis()),
            BigDecimal.valueOf(100.0), 12345678L, "1234-5678-9012-3456",
            new Time(System.currentTimeMillis()), "Dona da Loja A", lojaA);

      var transacao2 = new Transacao(
            2L, 1, new Date(System.currentTimeMillis()),
            BigDecimal.valueOf(50.0), 87654321L, "1234-5678-8012-3456",
            new Time(System.currentTimeMillis()), "Dona da Loja B", lojaB);

      var transacao3 = new Transacao(
            3L, 1, new Date(System.currentTimeMillis()),
            BigDecimal.valueOf(75.0), 213456789L, "1234-5678-0012-3456",
            new Time(System.currentTimeMillis()), "Dona da Loja A", lojaA);

      var mockTransacoes = List.of(transacao1, transacao2, transacao3);

      when(repository.findAllByOrderByNomeDaLojaAscIdDesc())
            .thenReturn(mockTransacoes);

      // 2º A - Act - Chama a operação que está sendo testada

      var reports = service.listTotaisTransacaoPorNomeDaLoja();

      // 3º A - Assert - Aferir o resultado esperado

      assertEquals(2, reports.size());

      reports.forEach(report -> {
         if (report.nomeDaLoja().equals(lojaA)) {
            assertEquals(2, report.transacoes().size());
            assertEquals(BigDecimal.valueOf(175.0), report.total());
            assertTrue(report.transacoes().contains(transacao1));
            assertTrue(report.transacoes().contains(transacao3));

         } else if (report.nomeDaLoja().equals(lojaB)) {
            assertEquals(1, report.transacoes().size());
            assertEquals(BigDecimal.valueOf(50.0), report.total());
            assertTrue(report.transacoes().contains(transacao2));
         }
      });
   }
}