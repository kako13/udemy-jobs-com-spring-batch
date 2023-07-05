package com.springbatch.arquivoxml.step;

import com.springbatch.arquivoxml.dominio.Cliente;
import org.springframework.batch.item.ItemWriter;

public interface ArquivoXMLWriter {
    ItemWriter<Cliente> xmlItemWriter();
}
