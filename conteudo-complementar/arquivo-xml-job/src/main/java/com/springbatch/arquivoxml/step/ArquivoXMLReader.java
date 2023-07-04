package com.springbatch.arquivoxml.step;

import com.springbatch.arquivoxml.dominio.Cliente;
import org.springframework.batch.item.ItemReader;
import org.springframework.oxm.xstream.XStreamMarshaller;

public interface ArquivoXMLReader {

    ItemReader<Cliente> xmlItemReader(XStreamMarshaller marshaller);
}
