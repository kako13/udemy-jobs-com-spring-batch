package com.udemy.parimparjob.util;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.stream.Collectors;

@Component
public class ListaDeInteirosRandomicos {

    /**
     *
     * @param elementos - quantidade de elementos inteiros a serem gerados ramdomicamente
     * @return lista de inteiros ramdomicos de valor máximo 100 vezes maior que o número da maior posição passada
     */

    public List<Integer> gera(Integer elementos) {
        Vector<Integer> vetorPosicoes = new Vector<>();
        vetorPosicoes.setSize(elementos);
        Random random = new Random();

        return vetorPosicoes.stream()
                .map(e -> random.nextInt(vetorPosicoes.size() * 100))
                .collect(Collectors.toList());
    }
}
