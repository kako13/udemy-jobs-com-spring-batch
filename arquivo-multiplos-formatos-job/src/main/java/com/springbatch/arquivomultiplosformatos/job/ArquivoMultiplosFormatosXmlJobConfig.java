package com.springbatch.arquivomultiplosformatos.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArquivoMultiplosFormatosXmlJobConfig {

	@Bean
	public Job arquivoMultiplosFormatosXmlJob(JobRepository jobRepository,
											  @Qualifier("leituraArquivoMultiplosFormatosXmlStep") Step leituraArquivoMultiplosFormatosStep) {
		return new JobBuilder("arquivoMultiplosFormatosXmlJob", jobRepository)
				.start(leituraArquivoMultiplosFormatosStep)
				.incrementer(new RunIdIncrementer())
				.build();
	}
}