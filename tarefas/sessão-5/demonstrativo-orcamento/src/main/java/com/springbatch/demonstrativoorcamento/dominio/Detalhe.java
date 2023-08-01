package com.springbatch.demonstrativoorcamento.dominio;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class Detalhe {
    private String data;
    private String descricaoItem;
    private BigDecimal valor;

    public static Detalhe fromDespesa(Despesa despesa) {
        return Detalhe.builder()
                .descricaoItem(despesa.getDescricaoItem())
                .data(despesa.getData())
                .valor(despesa.getValor())
                .build();
    }

    @Override
    public String toString() {
        return String.format("\t\t[%s] %s - R$ %s\n", data, descricaoItem, valor);
    }
}
