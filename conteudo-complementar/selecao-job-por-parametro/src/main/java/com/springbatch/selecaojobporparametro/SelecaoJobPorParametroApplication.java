package com.springbatch.selecaojobporparametro;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@EnableBatchProcessing
@SpringBootApplication
public class SelecaoJobPorParametroApplication  {

	public static void main(String[] args) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {

		ConfigurableApplicationContext ctx = SpringApplication.run(SelecaoJobPorParametroApplication.class, args);


		JobLauncher jobLauncher = ctx.getBean(JobLauncher.class);

		String jobName = null; // nome do job a partir dos argumentos da linha de comando
		String nome = null; // nome do arquivo utilizado no job, podendo ser de entrada ou saída

		for (String arg : args) {
			if (arg.startsWith("--jobName=")) {
				jobName = arg.substring("--jobName=".length());
			}
			if (arg.startsWith("responsible=")) {
				nome = arg.substring("responsible=".length());
				break;
			}
		}

		assert jobName != null;
		Job job = ctx.getBean(jobName, Job.class);
		System.out.println("Iniciando " + job);


		if (job != null) {
			JobParameters jobParameters = new JobParametersBuilder()
					.addString("timestamp", String.valueOf(System.currentTimeMillis()))
					.addString("nome", nome)
					.toJobParameters();

			jobLauncher.run(job, jobParameters); // Executa o job com os parâmetros
		} else {
			System.out.println("Job não encontrado: " + jobName);
		}

		System.exit(0);
	}
}