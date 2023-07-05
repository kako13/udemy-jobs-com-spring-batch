package com.springbatch.arquivoxml.step;

import com.springbatch.arquivoxml.dominio.Cliente;
import org.springframework.batch.item.ItemReader;

public interface ArquivoXMLReader {

    ItemReader<Cliente> xmlItemReader();
}
