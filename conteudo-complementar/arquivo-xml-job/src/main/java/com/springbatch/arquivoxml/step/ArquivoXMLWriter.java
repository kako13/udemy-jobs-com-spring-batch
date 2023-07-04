package com.springbatch.arquivoxml.step;

import com.springbatch.arquivoxml.dominio.Cliente;
import org.springframework.batch.item.ItemWriter;
import org.springframework.oxm.xstream.XStreamMarshaller;

public interface ArquivoXMLWriter {
    ItemWriter<Cliente> xmlItemWriter(XStreamMarshaller marshaller);
}
