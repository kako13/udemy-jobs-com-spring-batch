package com.springbatch.arquivomultiplosformatos.reader;

import com.springbatch.arquivomultiplosformatos.dominio.Cliente;
import com.springbatch.arquivomultiplosformatos.dominio.Transacao;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamReader;

public class ArquivoClienteTransacaoReader implements ItemStreamReader<Cliente> {

    private Object objetoAtual;
    private final ItemStreamReader<Object> delegate;

    public ArquivoClienteTransacaoReader(ItemStreamReader<Object> delegate) {
        this.delegate = delegate;
    }

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        this.delegate.open(executionContext);
    }

    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {
        this.delegate.update(executionContext);
    }

    @Override
    public void close() throws ItemStreamException {
        this.delegate.close();
    }

    @Override
    public Cliente read() throws Exception {

        if(objetoAtual == null)
            objetoAtual = delegate.read(); // será sempre um cliente, e será chamado apenas uma vez


        Cliente cliente = (Cliente) objetoAtual;
        objetoAtual = null;

        if(cliente != null) {
            // ler transacoes
            while (peek() instanceof Transacao) {
                cliente.getTransacoes().add((Transacao) objetoAtual);
            }
        }
        return cliente;
    }

    private Object peek() throws Exception {
        objetoAtual = delegate.read();
        return objetoAtual;
    }
}
