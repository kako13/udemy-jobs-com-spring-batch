package com.udemy.parimparjob;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.stream.Collectors;

@Configuration
public class ParImparBatchConfig {

	public static final int QUANTIDADE_ELEMENTOS_INTEIROS = 30000;
	public static final int TAMANHO_CHUNK = 30;

	@Bean
	public Job imprimeParImparJob(JobRepository jobRepository, Step step) {

		return new JobBuilder("imprimeParImparJob", jobRepository)
				.start(step)
				.incrementer(new RunIdIncrementer())
				.build();
	}

	@Bean
	public Step imprimeParImparStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("imprimeParImparStep", jobRepository)
				.chunk(TAMANHO_CHUNK, transactionManager)
				.reader(contaAteDezReader())
				.processor(parOuImparProcessor())
				.writer(imprimeWriter())
				.build();
	}


	public IteratorItemReader<Integer> contaAteDezReader() {
		Vector<Integer> vetorPosicoes = new Vector<>();
		vetorPosicoes.setSize(QUANTIDADE_ELEMENTOS_INTEIROS);
		List<Integer> elementos = geraListaDeInteirosRandomicos(vetorPosicoes);
		return new IteratorItemReader<>(elementos.iterator());
	}

	private ItemProcessor<? super Object, ?> parOuImparProcessor() {
        return item -> (Integer) item % 2 == 0
                        ? String.format("Item %s é par", item)
                        : String.format("Item %s é ímpar", item);
	}

    private ItemWriter<? super Object> imprimeWriter() {
        return itens -> itens.forEach(System.out::println);
    }

	private List<Integer> geraListaDeInteirosRandomicos(Vector<Integer> posicoes) {
		Random random = new Random();
		return posicoes.stream()
				.map(e -> random.nextInt(posicoes.size() * 100))
				.collect(Collectors.toList());
	}
}
