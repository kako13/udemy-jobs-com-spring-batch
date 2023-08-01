package com.springbatch.demonstrativoorcamento.reader;


import com.springbatch.demonstrativoorcamento.dominio.Despesa;
import com.springbatch.demonstrativoorcamento.dominio.Detalhe;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.ResourceAwareItemReaderItemStream;
import org.springframework.core.io.Resource;

import java.util.Objects;

public class DemonstrativoOrcamentarioItemDespesaReader implements ItemStreamReader<Despesa>, ResourceAwareItemReaderItemStream<Despesa> {

    private Despesa objetoAtual;
    private FlatFileItemReader<Despesa> delegate;

    public DemonstrativoOrcamentarioItemDespesaReader(FlatFileItemReader<Despesa> delegate) {
        this.delegate = delegate;
    }

    @Override
    public Despesa read() throws Exception {
        if (objetoAtual == null)
            objetoAtual = delegate.read(); // ler objeto


        Despesa despesa = objetoAtual;
        objetoAtual = null;

        if (despesa != null) {
            despesa.getDetalhes().add(Detalhe.fromDespesa(despesa));
            while (peek() != null && Objects.equals(objetoAtual.getCodigoNatureza(), despesa.getCodigoNatureza())) {
                    despesa.getDetalhes().add(Detalhe.fromDespesa(objetoAtual));
            }
        }
        return despesa;
    }

    private Despesa peek() throws Exception {
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

    @Override
    public void setResource(Resource resource) {
        delegate.setResource(resource);
    }
}
