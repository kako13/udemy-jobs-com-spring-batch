package com.springbatch.arquivojson.step;

import com.springbatch.arquivojson.dominio.ClienteListWrapper;
import org.springframework.batch.item.ItemWriter;

public interface ArquivoJsonWriter {
    ItemWriter<ClienteListWrapper> jsonItemWriter();
}
