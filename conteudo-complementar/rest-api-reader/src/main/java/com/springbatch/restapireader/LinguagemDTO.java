package com.springbatch.restapireader;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class LinguagemDTO {

    private String title;
    private String image;
    private int ranking;

    public String toString() {
        return "Linguagem{" +
                    "title ='" + title + "'" +
                    ", image='" + image + "'" +
                    ", ranking='" + ranking + "'" +
                    '}';
    }
}
