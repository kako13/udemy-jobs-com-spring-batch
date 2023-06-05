package com.udemy.parimparjob;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.*;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Arrays;
import java.util.List;

@Configuration
public class ParImparBatchConfig {

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
				.chunk(3, transactionManager)
				.reader(contaAteDezReader())
				.processor(parOuImparProcessor())
				.writer(imprimeWriter())
				.build();
	}

    @Bean
	@StepScope
	public IteratorItemReader<Integer> contaAteDezReader() {
		List<Integer> numerosDeUmAteDez = Arrays.asList(1, 2, 3, 4, 5, 6, 8, 9, 10);
		return new IteratorItemReader<>(numerosDeUmAteDez.iterator());
	}

    @Bean
    @StepScope
	public ItemProcessor<? super Object, ?> parOuImparProcessor() {
        return item -> (Integer) item % 2 == 0
                        ? String.format("Item %s é par", item)
                        : String.format("Item %s é impar", item);
	}

    private ItemWriter<? super Object> imprimeWriter() {
        return itens -> itens.forEach(System.out::println);
    }
}
