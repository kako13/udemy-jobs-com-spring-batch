package com.springbatch.kafkaproducer.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbatch.kafkaproducer.dominio.Pessoa;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PessoaSerializer implements Serializer<Pessoa> {

    private static final Logger LOG = LoggerFactory.getLogger(PessoaSerializer.class);

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(String topic , Pessoa pessoa) {
        try {
            if (pessoa == null){
                LOG.info("Null received at serializing pessoa");
                return null;
            }
            LOG.info("Serializing..." + pessoa);
            return objectMapper.writeValueAsBytes(pessoa);
        } catch (Exception e) {
            LOG.error("Error when serializing Update to byte[]");
            throw new SerializationException("Error when serializing Update to byte[]");
        }
    }
}
