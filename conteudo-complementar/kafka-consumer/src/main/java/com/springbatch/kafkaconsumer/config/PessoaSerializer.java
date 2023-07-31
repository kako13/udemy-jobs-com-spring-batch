package com.springbatch.kafkaconsumer.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbatch.kafkaconsumer.dominio.Pessoa;
import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.kafka.support.serializer.DeserializationException;

public class PessoaSerializer implements Deserializer<Pessoa> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Pessoa deserialize(String topic, byte[] data) {
        try {
            if (data == null){
                System.out.println("Null received at deserializing pessoa");
                return null;
            }
            System.out.println("Deserializing...");
            return objectMapper.readValue(data, Pessoa.class);

        } catch (Exception e) {
            throw new DeserializationException("Error when deserializing byte[] to Update", data, false, e);
        }
    }
}
