package com.springbatch.demonstrativoorcamento.dominio;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class Item {
    private String data;
    private String descricaoItem;
    private BigDecimal valor;

    public static Item fromLancamento(Lancamento lancamento) {
        return Item.builder()
                .descricaoItem(lancamento.getDescricaoItem())
                .data(lancamento.getData())
                .valor(lancamento.getValor())
                .build();
    }

    @Override
    public String toString() {
        return String.format("\t\t[%s] %s - R$ %s\n", data, descricaoItem, valor);
    }
}
