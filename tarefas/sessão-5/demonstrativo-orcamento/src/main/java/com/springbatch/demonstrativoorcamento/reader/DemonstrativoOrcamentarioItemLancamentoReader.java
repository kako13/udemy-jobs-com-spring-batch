package com.springbatch.demonstrativoorcamento.reader;


import com.springbatch.demonstrativoorcamento.dominio.Lancamento;
import com.springbatch.demonstrativoorcamento.dominio.Item;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.database.JdbcCursorItemReader;

import java.util.Objects;

public class DemonstrativoOrcamentarioItemLancamentoReader implements ItemStreamReader<Lancamento> {

    private Lancamento objetoAtual;
    private JdbcCursorItemReader<Lancamento> delegate;

    public DemonstrativoOrcamentarioItemLancamentoReader(JdbcCursorItemReader<Lancamento> delegate) {
        this.delegate = delegate;
    }

    @Override
    public Lancamento read() throws Exception {
        if (objetoAtual == null)
            objetoAtual = delegate.read(); // ler objeto


        Lancamento lancamento = objetoAtual;
        objetoAtual = null;

        if (lancamento != null) {
            lancamento.getItems().add(Item.fromLancamento(lancamento));
            while (peek() != null && Objects.equals(objetoAtual.getCodigoNaturezaDespesa(), lancamento.getCodigoNaturezaDespesa())) {
                    lancamento.getItems().add(Item.fromLancamento(objetoAtual));
            }
        }
        return lancamento;
    }

    private Lancamento peek() throws Exception {
        objetoAtual = delegate.read();//leitura do proximo item
        return objetoAtual;
    }

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        delegate.open(executionContext);
    }

    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {
        delegate.update(executionContext);
    }

    @Override
    public void close() throws ItemStreamException {
        delegate.close();
    }
}
