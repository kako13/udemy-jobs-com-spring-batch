package com.springbatch.demonstrativoorcamento.reader;


import com.springbatch.demonstrativoorcamento.dominio.GrupoLancamento;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.ResourceAwareItemReaderItemStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Objects;

@Component
public class DemonstrativoOrcamentarioItemLancamentoReader implements ItemStreamReader<GrupoLancamento>, ResourceAwareItemReaderItemStream<GrupoLancamento> {

    private GrupoLancamento objetoAtual;
    @Autowired
    // Esse aqui lê do arquivo
//    private FlatFileItemReader<GrupoLancamento> delegate;
    // Esse aqui lê do banco
    private JdbcCursorItemReader<GrupoLancamento> delegate;


    @Override
    public GrupoLancamento read() throws Exception {
        if (objetoAtual == null)
            objetoAtual = delegate.read(); // ler objeto


        GrupoLancamento grupoLancamento = objetoAtual;
        objetoAtual = null;

        if (grupoLancamento != null) {
            grupoLancamento.setLancamentos(new ArrayList<>());
            while (isLancamentoRelacionado(grupoLancamento)) {
                grupoLancamento.getLancamentos().add(objetoAtual.getLancamentoAux());
            }
            grupoLancamento.getLancamentos().add(grupoLancamento.getLancamentoAux());
        }
        return grupoLancamento;
    }

    private boolean isLancamentoRelacionado(GrupoLancamento grupoLancamento) throws Exception {
        return peek() != null && Objects.equals(objetoAtual.getCodigoNaturezaDespesa(), grupoLancamento.getCodigoNaturezaDespesa());
    }

    private GrupoLancamento peek() throws Exception {
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
        // Descomentar para a leitura de arquivo
//        delegate.setResource(resource);
    }
}