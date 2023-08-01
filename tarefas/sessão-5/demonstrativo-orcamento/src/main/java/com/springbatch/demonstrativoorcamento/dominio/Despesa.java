package com.springbatch.demonstrativoorcamento.dominio;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Data
public class Despesa {

    private String codigoNatureza;
    private String descricaoNatureza;
    private String descricaoItem;
    private String data;
    private BigDecimal valor;
    private List<Detalhe> detalhes = new ArrayList<>();

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        formataCabecalho(stringBuilder);
        formataElementos(stringBuilder);

        return stringBuilder.toString();
    }

    private void formataElementos(StringBuilder stringBuilder) {
        detalhes.stream().sorted(Comparator.comparing(Detalhe::getData)).forEach(stringBuilder::append);
    }

    private void formataCabecalho(StringBuilder stringBuilder) {
        stringBuilder.append("---- Demonstrativo orçamentário ----\n");
        stringBuilder.append(String.format("[%s] %s - R$ %s\n", codigoNatureza, descricaoNatureza, somaValores()));
    }

    private BigDecimal somaValores() {
        return detalhes.stream()
                .map(Detalhe::getValor)
                .reduce(BigDecimal::add).get();
    }
}
