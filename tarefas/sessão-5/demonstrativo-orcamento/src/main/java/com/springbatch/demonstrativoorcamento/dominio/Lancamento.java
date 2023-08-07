package com.springbatch.demonstrativoorcamento.dominio;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;


@Data
@Builder
public class Lancamento {

    private String descricaoLancamento;
    private Date dataLancamento;
    private BigDecimal valorLancamento;

    @Override
    public String toString() {
        return String.format("\t\t[%s] %s - R$ %s\n", dataLancamento, descricaoLancamento, valorLancamento);
    }
}
