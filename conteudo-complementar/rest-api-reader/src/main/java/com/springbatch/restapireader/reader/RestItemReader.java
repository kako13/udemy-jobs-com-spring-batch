package com.springbatch.restapireader.reader;

import com.springbatch.restapireader.LinguagemDTO;
import org.springframework.batch.item.ItemReader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class RestItemReader implements ItemReader<LinguagemDTO> {

    private static String apiUrl;
    private static RestTemplate restTemplate;


    private int nextLinguagemIndex;
    private List<LinguagemDTO> linguagemData;

    public RestItemReader(String apiUrl, RestTemplate restTemplate) {
        this.apiUrl = apiUrl;
        this.restTemplate = restTemplate;
        this.nextLinguagemIndex = 0;
    }

    @Override
    public LinguagemDTO read() throws Exception {

        linguagemData = linguagemData == null
                ? fetchDataFromAPI() // Deve ser chamada apenas uma vez
                : linguagemData;

        LinguagemDTO nextLinguagem = null;

        if (nextLinguagemIndex < linguagemData.size()) {
            nextLinguagem = linguagemData.get(nextLinguagemIndex);
            nextLinguagemIndex++;
        } else {
            nextLinguagemIndex = 0;
            linguagemData = null;
        }
        return nextLinguagem;
    }

    private static List<LinguagemDTO> fetchDataFromAPI() {
        ResponseEntity<LinguagemDTO[]> responseEntity = restTemplate.getForEntity(apiUrl, LinguagemDTO[].class);

        boolean xxSuccessful = responseEntity.getStatusCode().is2xxSuccessful();
        if (xxSuccessful)
            return List.of(responseEntity.getBody());
        return null;
    }
}
