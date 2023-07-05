package com.springbatch.arquivojson.dominio;

import lombok.Data;

import java.util.List;

@Data
public class ClienteListWrapper {
    private List<Cliente> clientes;

    @Override
    public String toString() {
        return "ClienteListWrapper{" +
                "clientes=" + clientes +
                '}';
    }
}
