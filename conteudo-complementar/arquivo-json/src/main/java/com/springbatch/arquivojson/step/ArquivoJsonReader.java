package com.springbatch.arquivojson.step;

import com.springbatch.arquivojson.dominio.ClienteListWrapper;
import org.springframework.batch.item.ItemReader;

public interface ArquivoJsonReader {

    ItemReader<ClienteListWrapper> jsonItemReader();
}
