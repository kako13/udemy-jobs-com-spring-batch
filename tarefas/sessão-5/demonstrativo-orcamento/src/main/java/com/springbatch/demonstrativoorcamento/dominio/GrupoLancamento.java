package com.springbatch.demonstrativoorcamento.dominio;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;


@Data
@Builder
public class GrupoLancamento {
    private Integer codigoNaturezaDespesa;
    private String descricaoNaturezaDespesa;
    private List<Lancamento> lancamentos;

    private Lancamento lancamentoAux;

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        formataCabecalho(stringBuilder);
        formataElementos(stringBuilder);

        return stringBuilder.toString();
    }
    
    private void formataCabecalho(StringBuilder stringBuilder) {
        stringBuilder.append("---- Demonstrativo orçamentário ----\n");
        stringBuilder.append(String.format("[%s] %s - R$ %s\n", codigoNaturezaDespesa, descricaoNaturezaDespesa, somaValores()));
    }
    
    private void formataElementos(StringBuilder stringBuilder) {
        lancamentos.stream()
                .sorted(Comparator.comparing(Lancamento::getDataLancamento))
                .forEach(stringBuilder::append);
    }
    
    private BigDecimal somaValores() {
        return lancamentos.stream()
                .map(Lancamento::getValorLancamento)
                .reduce(BigDecimal::add).get();
    }
}

