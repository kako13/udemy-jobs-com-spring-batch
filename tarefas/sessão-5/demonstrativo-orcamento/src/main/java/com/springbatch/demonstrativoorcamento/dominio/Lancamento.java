package com.springbatch.demonstrativoorcamento.dominio;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Data
public class Lancamento {

    private String codigoNaturezaDespesa;
    private String descricaoNaturezaDespesa;
    private String descricaoLancamento;
    private String dataLancamento;
    private BigDecimal valorLancamento;
    private List<Item> items = new ArrayList<>();

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        formataCabecalho(stringBuilder);
        formataElementos(stringBuilder);

        return stringBuilder.toString();
    }

    private void formataElementos(StringBuilder stringBuilder) {
        items.stream().sorted(Comparator.comparing(Item::getData)).forEach(stringBuilder::append);
    }

    private void formataCabecalho(StringBuilder stringBuilder) {
        stringBuilder.append("---- Demonstrativo orçamentário ----\n");
        stringBuilder.append(String.format("[%s] %s - R$ %s\n", codigoNaturezaDespesa, descricaoNaturezaDespesa, somaValores()));
    }

    private BigDecimal somaValores() {
        return items.stream()
                .map(Item::getValor)
                .reduce(BigDecimal::add).get();
    }
}
